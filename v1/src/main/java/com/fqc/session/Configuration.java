package com.fqc.session;

import com.fqc.binding.MapperRegister;
import com.fqc.mapping.MappedStatement;
import com.fqc.session.defaults.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis的大管家，主要有全局配置，组件管理和简单工厂的功能
 */
public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegister mapperRegistry =new MapperRegister(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
}
