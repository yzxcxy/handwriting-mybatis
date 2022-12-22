package com.fqc.executor;

import com.fqc.session.mapping.BoundSql;
import com.fqc.session.mapping.MappedStatement;
import com.fqc.transaction.Transaction;
import com.fqc.session.ResultHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 执行器接口
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}