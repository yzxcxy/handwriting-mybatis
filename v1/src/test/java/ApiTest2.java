import com.fqc.binding.MapperRegister;
import com.fqc.session.SqlSession;
import com.fqc.session.defaults.DefaultSqlSessionFactory;
import dao.ISchoolDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiTest2 {
    private Logger logger = LoggerFactory.getLogger(ApiTest2.class);

    @Test
    public void test_MapperSqlSessionFactory(){
/*        MapperRegister mapperRegister = new MapperRegister();
        mapperRegister.addMappers("dao");
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegister);
        SqlSession sqlSession = defaultSqlSessionFactory.openSqlSession();
        ISchoolDao mapper = sqlSession.getMapper(ISchoolDao.class);
        String schoolName = mapper.querySchoolName("xxxx");
        logger.debug("test result is: "+schoolName);*/
        //res: 11:59:57.657 [main] DEBUG ApiTest2 - test result is: 你被代理了，方法：querySchoolName 参数：[Ljava.lang.Object;@1bc6a36e
    }
}
