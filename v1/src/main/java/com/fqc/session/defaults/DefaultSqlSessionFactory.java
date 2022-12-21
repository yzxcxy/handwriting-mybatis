package com.fqc.session.defaults;

import com.fqc.binding.MapperRegister;
import com.fqc.session.Configuration;
import com.fqc.session.SqlSession;
import com.fqc.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
