package com.fqc.binding;

import cn.hutool.core.lang.ClassScanner;
import com.fqc.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 提供了一种扫描包路径然后将mapper代理注册到缓存中
 */
public class MapperRegister {
    //缓存
    private Map<Class<?>,MapperProxyFactory<?>> konwnMappers=new HashMap<>();

    //获取注册的代理
    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) konwnMappers.get(type);
        //如果为空报错
        if(mapperProxyFactory==null){
            throw  new RuntimeException("Type"+type+"is known to the MapperRegister");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance. Casue:"+e,e);
        }
    }

    //根据给定的包路径扫描，然后给接口创建映射器代理类
    public void addMappers(String packagePath){
        Set<Class<?>> classes = ClassScanner.scanPackage(packagePath);
        for (Class<?> aClass : classes) {
            addMapper(aClass);
        }
    }

    //添加到缓存中
    public <T> void addMapper(Class<T> type){
       if(type.isInterface()){
           if(hasMapper(type)){
               throw new RuntimeException("Type "+type+" is already known to the MapperRegister");
           }
           konwnMappers.put(type,new MapperProxyFactory<>(type));
       }
    }

    private <T> boolean hasMapper(Class<T> type) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) konwnMappers.get(type);
        if(mapperProxyFactory==null)
            return true;
        else
            return false;
    }
}
