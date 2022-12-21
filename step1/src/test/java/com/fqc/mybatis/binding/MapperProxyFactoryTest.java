package com.fqc.mybatis.binding;

import dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MapperProxyFactoryTest {

    private final Logger logger= LoggerFactory.getLogger(MapperProxyFactoryTest.class);

    @Test
    public void test_mapperProxyFactory(){
        MapperProxyFactory<IUserDao> proxyFactory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> map = new HashMap<>();
        map.put("dao.IUserDao.queryUserName","执行xml中的操作：查询用户");
        map.put("dao.IUserDao.queryUserAge","执行xml中的操作：查询用户年龄");
        IUserDao userDao = proxyFactory.newInstance(map);
        String res = userDao.queryUserAge();
        logger.debug("{}",res);
    }
}