package com.fqc.session.defaults;

import com.fqc.binding.MapperRegister;
import com.fqc.session.SqlSession;
import com.fqc.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private MapperRegister mapperRegister;

    public DefaultSqlSessionFactory(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(mapperRegister);
    }
}
