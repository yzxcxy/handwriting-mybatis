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
import java.io.Reader;

public class ApiTest3 {

    private Logger logger = LoggerFactory.getLogger(ApiTest3.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = factoryBuilder.build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User res = mapper.queryUserInfoById(1L);
        logger.debug("result is : "+res);
    }
}
