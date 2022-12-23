package com.fqc.scripting;


import com.fqc.executor.parameter.ParameterHandler;
import com.fqc.mapping.BoundSql;
import com.fqc.mapping.MappedStatement;
import com.fqc.mapping.SqlSource;
import com.fqc.session.Configuration;
import org.dom4j.Element;


/**
 * 脚本语言驱动接口
 * 提供创建 SQL 信息的方法，入参包括了配置、元素、参数。
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
