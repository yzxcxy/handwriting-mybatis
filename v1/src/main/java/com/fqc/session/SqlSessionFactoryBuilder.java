package com.fqc.session;

import com.fqc.builder.xml.XMLConfigBuilder;
import com.fqc.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 建造者模式，创建SqlSessionFactory
 * 入口
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
