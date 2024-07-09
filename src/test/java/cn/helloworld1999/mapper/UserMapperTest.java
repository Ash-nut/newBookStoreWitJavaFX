package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
}
