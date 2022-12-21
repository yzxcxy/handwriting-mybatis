package com.fqc.session;

/**
 * mybatis的顶层接口，表示一个数据库会话,用于定义执行 SQL 标准、获取映射器以及将来管理事务等方面的操作。
 */
public interface SqlSession {
    /**
     * 根据sqlId来获取一条记录
     * @param statement sqlId
     * @return 返回封装之后的结果对象
     * @param <T> 封装之后的结果类型
     */
    <T> T selectOne(String statement);

    /**
     * 根据sqlId和sql参数获取一条记录
     * @param statement sqlId
     * @param parameter sql参数
     * @return 返回封装之后的结果对象
     * @param <T> 封装之后的结果类型
     */
    <T> T selectOne(String statement,Object parameter);

    /**
     * 根据类型返回一个映射器
     * @param type 被代理接口的Class对象
     * @return 该类型的代理
     * @param <T> 被代理接口类型
     */
    <T> T getMapper(Class<T> type);

    Configuration getConfiguration();
}
