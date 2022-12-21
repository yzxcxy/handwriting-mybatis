package com.fqc.binding;

import com.fqc.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 *封装创建代理的操作，使得我们不需要自己使用Proxy.newInstace()创建代理对象
 * @param <T>
 */
public class MapperProxyFactory<T> {
    // 需要被代理的用户自定义的数据库访问接口
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession){
        // 创建需要被代理的对象
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        //返回创建的最终的代理对象
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
