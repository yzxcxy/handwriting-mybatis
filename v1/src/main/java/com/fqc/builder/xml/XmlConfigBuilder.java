package com.fqc.builder.xml;

import com.fqc.builder.BaseBuilder;
import com.fqc.datasource.DataSourceFactory;
import com.fqc.io.Resources;
import com.fqc.session.mapping.BoundSql;
import com.fqc.session.mapping.Environment;
import com.fqc.session.mapping.MappedStatement;
import com.fqc.session.mapping.SqlCommandType;
import com.fqc.session.Configuration;
import com.fqc.transaction.TransactionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析mybatis-config文件，在这个类中还会生成Configuration对象
 */
public class XmlConfigBuilder extends BaseBuilder {
    private Element root;

    public XmlConfigBuilder(Reader reader) {
        // 1. 调用父类初始化Configuration
        super(new Configuration());
        // 2. dom4j 处理 xml(mybatis-config)
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析配置；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     */
    public Configuration parse() {
        try{
            //解析数据库环境
            environmentsElement(root.element("environments"));
            //解析mapper文件
            mapperElement(root.element("mappers"));
        }catch (Exception e){
            throw new RuntimeException("Error parsing SQL Mapper Configuration.Cause:"+e);
        }
        //这个类的最终任务就是解析填充返回一个Configuration
        return configuration;
    }

    /**
     * 解析数据库环境
     *      * <environments default="development">
     *      * <environment id="development">
     *      * <transactionManager type="JDBC">
     *      * <property name="..." value="..."/>
     *      * </transactionManager>
     *      * <dataSource type="POOLED">
     *      * <property name="driver" value="${driver}"/>
     *      * <property name="url" value="${url}"/>
     *      * <property name="username" value="${username}"/>
     *      * <property name="password" value="${password}"/>
     *      * </dataSource>
     *      * </environment>
     *      * </environments>
     * @param context 数据库多环境节点
     */
    private void environmentsElement(Element context) throws InstantiationException, IllegalAccessException {
        String environment = context.attributeValue("default");
        List<Element> elementList = context.elements("environment");
        for (Element element : elementList) {
            String id = element.attributeValue("id");
            if(environment.equals(id)){
                //根据类型注册机反射获取事务管理工厂
                TransactionFactory transactionFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(element.element("transactionManager").attributeValue("type")).newInstance();
                //数据源
                Element dataSourceElement = element.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();
                // 构建环境
                Environment.Builder environmentBuilder = new Environment.Builder(id)
                        .transactionFactory(transactionFactory)
                        .dataSource(dataSource);
                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }

    /**
     *     解析mapper元素为一个mapperStatement,并且注册到Configuration
     */
    private void mapperElement(Element mappers) throws IOException, DocumentException, ClassNotFoundException {
        List<Element> elementList = mappers.elements("mapper");
        for (Element e : elementList) {
            //使用dom4j读取xml文件
            String resource = e.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            //命名空间
            String namespace = root.attributeValue("namespace");
            // 读取SELECT语句配置
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
                //获取xml节点数据
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();
                // ? 匹配（#{}）
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find(); i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameter.put(i, g2);
                    sql = sql.replace(g1, "?");
                }
                //以命名空间和接口id作为其mapperStatement的id
                String msId = namespace + "." + id;
                //标签名
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                BoundSql boundSql = new BoundSql(sql, parameter, parameterType, resultType);

                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, boundSql).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);
            }
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
