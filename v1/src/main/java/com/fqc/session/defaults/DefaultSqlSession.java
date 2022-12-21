package com.fqc.session.defaults;

import com.fqc.session.Configuration;
import com.fqc.session.SqlSession;

/**
 * mybatis提供的SqlSession的默认实现
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T)("你被代理了！,方法: "+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T)("你被代理了，方法："+statement+" 参数："+parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
