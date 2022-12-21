package com.fqc.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理接口（一次数据库的操作应该具有事务管理的功能）
 */
public interface Transaction {
    /**
     * 获取连接
     * @return 数据库连接
     */
    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
