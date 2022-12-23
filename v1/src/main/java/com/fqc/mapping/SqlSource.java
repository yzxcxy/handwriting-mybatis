package com.fqc.mapping;

/**
 * SQL源码
 */
public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
