package com.fqc.executor;

import com.fqc.mapping.BoundSql;
import com.fqc.mapping.MappedStatement;
import com.fqc.session.RowBounds;
import com.fqc.transaction.Transaction;
import com.fqc.session.ResultHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器接口
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}