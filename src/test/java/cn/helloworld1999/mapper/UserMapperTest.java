package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class UserMapperTest extends BaseMapperTest {
    @Test
    //对 根据account查找User 和 根据 User 添加 User 功能进行测试
    public void testSelectByAccountAndInsertUser() {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setAccount("测试");
            userMapper.insertUser(user);
            sqlSession.commit();
            user = userMapper.selectUserByAccount(user);
            Assert.assertNotNull(user);
            Assert.assertEquals("测试", user.getAccount());
            sqlSession.rollback();
        }
    }
    @Test
    public void testUpdateUserSelective() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 准备工作，新加一个 User
            User user = new User("hahaha2333", "123456", 100.0, 99, "19961412333");
            userMapper.insertUser(user);
            sqlSession.commit();
            // 开始测试
            User user1 = userMapper.selectUserByAccount(user);
            Assert.assertNotNull(user1); //拿到了
            user1.setBalance(1024.0);
            userMapper.updateUserSelective(user1);//更新
            sqlSession.commit();
            //检查
            User user2 = userMapper.selectUserByAccount(user1);
            Assert.assertNotNull(user2);
            Assert.assertEquals(Double.valueOf(1024.0), user2.getBalance());
            sqlSession.rollback();
        }
    }
}
