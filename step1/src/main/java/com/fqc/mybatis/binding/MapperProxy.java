package com.fqc.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    //需要执行的sql语句
    private final Map<String,String> sqlSession;
    //被代理的用户定义的数据库操作接口
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            //Object提供的hashCode和ToString等方法是不需要被代理的
            return method.invoke(this,args);
        }else {
            //以代理接口名+方法名座位key
            return "你的被代理了"+sqlSession.get(mapperInterface.getName()+"."+method.getName());
        }
    }
}
