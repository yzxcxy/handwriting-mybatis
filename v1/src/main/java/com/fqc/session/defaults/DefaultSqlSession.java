package com.fqc.session.defaults;

import com.fqc.executor.Executor;
import com.fqc.session.mapping.MappedStatement;
import com.fqc.session.Configuration;
import com.fqc.session.SqlSession;

import java.util.List;

/**
 * mybatis提供的SqlSession的默认实现
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration,Executor executor) {
        this.configuration = configuration;
        this.executor=executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return list.get(0);
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
