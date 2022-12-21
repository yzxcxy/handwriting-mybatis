package com.fqc.session.defaults;

import com.fqc.binding.MapperRegister;
import com.fqc.session.SqlSession;

/**
 * mybatis提供的SqlSession的默认实现
 */
public class DefaultSqlSession implements SqlSession {

    private final MapperRegister mapperRegister;

    public DefaultSqlSession(MapperRegister mapperRegister) {
        this.mapperRegister = mapperRegister;
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
        return mapperRegister.getMapper(type,this);
    }
}
