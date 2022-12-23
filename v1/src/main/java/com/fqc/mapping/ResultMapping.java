package com.fqc.mapping;

import com.fqc.session.Configuration;
import com.fqc.type.JdbcType;
import com.fqc.type.TypeHandler;

/**
 * 结果类型的子属性映射
 * TODO
 */
public class ResultMapping {
    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();
    }
}
