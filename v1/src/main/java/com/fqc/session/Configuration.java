package com.fqc.session;

import com.fqc.binding.MapperRegister;
import com.fqc.datasource.durid.DruidDataSourceFactory;
import com.fqc.mapping.Environment;
import com.fqc.mapping.MappedStatement;
import com.fqc.transaction.jdbc.JdbcTransactionFactory;
import com.fqc.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis的大管家，主要有全局配置，组件管理和简单工厂的功能
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegister mapperRegistry =new MapperRegister(this);

    /**
     * 映射的语句，存在Map里,key为接口的全路径名+"."+方法名
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * 环境，包括事务管理工厂等等
     */
    protected Environment environment;

    /**
     * 类型别名注册机
     */
    protected TypeAliasRegistry typeAliasRegistry=new TypeAliasRegistry();


    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void setTypeAliasRegistry(TypeAliasRegistry typeAliasRegistry) {
        this.typeAliasRegistry = typeAliasRegistry;
    }
}
