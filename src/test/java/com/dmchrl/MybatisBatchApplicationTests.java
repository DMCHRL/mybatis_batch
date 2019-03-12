package com.dmchrl;

import com.dmchrl.dao.UserDao;
import com.dmchrl.pojo.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisBatchApplicationTests {

  @Autowired
  private UserDao userDao;

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate;

	@Test
	public void contextLoads() {
	}

  @Test
  public void addUser() {
    for(int i = 0;i<1000; i++){
      userDao.addUser(new User(UUID.randomUUID().toString().substring(0,5),12));
    }
//    userDao.addUser(new User("张三",12));
  }

  /**
   * 批量插入
   */
  @Test
  public void addUserBatch() {
    SqlSession batchSqlSession = null;
    try {
      batchSqlSession = this.sqlSessionTemplate
        .getSqlSessionFactory()
        .openSession(ExecutorType.BATCH, false);
      UserDao userDao = batchSqlSession.getMapper(UserDao.class);
      Long start = System.currentTimeMillis();
      for(int i = 0;i<1000; i++){
        userDao.addUser(new User(UUID.randomUUID().toString().substring(0,5),12));
      }
      batchSqlSession.commit();
      Long end = System.currentTimeMillis();
      System.out.print(end-start);
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      try {
        batchSqlSession.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    }

}
