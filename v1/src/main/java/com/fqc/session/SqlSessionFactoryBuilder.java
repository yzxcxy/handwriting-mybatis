package com.fqc.session;

import com.fqc.builder.xml.XmlConfigBuilder;
import com.fqc.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 建造者模式，创建SqlSessionFactory
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
