package com.fqc.transaction.jdbc;

import com.fqc.session.TransactionIsolationLevel;
import com.fqc.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC事务管理器
 */
public class JdbcTransaction implements Transaction {

    protected DataSource dataSource;
    protected Connection connection;
    protected TransactionIsolationLevel level=TransactionIsolationLevel.NONE;
    protected boolean autoCommit;

    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        this.dataSource = dataSource;
        this.level = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(dataSource!=null){
            connection=dataSource.getConnection();
            connection.setAutoCommit(autoCommit);
            connection.setTransactionIsolation(level.getLevel());
        }
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if(connection!=null&&!connection.getAutoCommit()){
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if(connection!=null&&!connection.getAutoCommit()){
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if(connection!=null&&!connection.getAutoCommit()){
            connection.close();
        }
    }
}
