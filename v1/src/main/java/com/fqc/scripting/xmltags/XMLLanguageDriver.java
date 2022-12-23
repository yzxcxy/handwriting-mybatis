package com.fqc.scripting.xmltags;


import com.fqc.executor.parameter.ParameterHandler;
import com.fqc.mapping.BoundSql;
import com.fqc.mapping.MappedStatement;
import com.fqc.mapping.SqlSource;
import com.fqc.scripting.LanguageDriver;
import com.fqc.scripting.defaults.DefaultParameterHandler;
import com.fqc.session.Configuration;
import org.dom4j.Element;


public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}