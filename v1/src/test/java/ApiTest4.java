import com.alibaba.fastjson.JSON;
import com.fqc.io.Resources;
import com.fqc.session.SqlSession;
import com.fqc.session.SqlSessionFactory;
import com.fqc.session.SqlSessionFactoryBuilder;
import dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import po.User;

import java.io.IOException;

public class ApiTest4 {
    private Logger logger = LoggerFactory.getLogger(ApiTest4.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User res = mapper.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

    @Test
    public void test_SqlSessionFactory2() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        for (int i = 0; i < 50; i++) {
            User user = mapper.queryUserInfoById(1L);
            logger.info("测试结果：{}", JSON.toJSONString(user));
        }
    }
}
