package com.fqc.session;

import com.fqc.binding.MapperRegister;
import com.fqc.datasource.durid.DruidDataSourceFactory;
import com.fqc.datasource.pooled.PooledDataSourceFactory;
import com.fqc.datasource.upooled.UnpooledDataSourceFactory;
import com.fqc.executor.Executor;
import com.fqc.executor.SimpleExecutor;
import com.fqc.executor.resultset.DefaultResultSetHandler;
import com.fqc.executor.resultset.ResultSetHandler;
import com.fqc.executor.statement.PreparedStatementHandler;
import com.fqc.executor.statement.StatementHandler;
import com.fqc.session.mapping.BoundSql;
import com.fqc.session.mapping.Environment;
import com.fqc.session.mapping.MappedStatement;
import com.fqc.transaction.Transaction;
import com.fqc.transaction.jdbc.JdbcTransactionFactory;
import com.fqc.type.TypeAliasRegistry;

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
     * 映射的语句，存在Map里,key为接口的全路径名+"."+方法名
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * 环境，包括事务管理工厂等等
     */
    protected Environment environment;

    /**
     * 类型别名注册机
     */
    protected TypeAliasRegistry typeAliasRegistry=new TypeAliasRegistry();


    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        //新增两个数据源类型
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void setTypeAliasRegistry(TypeAliasRegistry typeAliasRegistry) {
        this.typeAliasRegistry = typeAliasRegistry;
    }

    /**
     * 创建语句处理器
     */
    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, resultHandler, boundSql);
    }

    /**
     * 创建结果集处理器
     */
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, boundSql);
    }

    /**
     * 生产执行器
     */
    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }

}
