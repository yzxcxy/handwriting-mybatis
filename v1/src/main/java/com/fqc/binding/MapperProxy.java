package com.fqc.binding;

import com.fqc.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    //需要执行的sql语句
    private final SqlSession sqlSession;
    //被代理的用户定义的数据库操作接口
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            //Object提供的hashCode和ToString等方法是不需要被代理的
            return method.invoke(this,args);
        }else {
            return sqlSession.selectOne(method.getName(),args);
        }
    }
}
