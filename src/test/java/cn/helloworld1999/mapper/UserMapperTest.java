package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.SysRole;
import cn.helloworld1999.bean.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            //我好像懂这段代码了，这段代码的意思就是通过某种“神秘工厂”拿到了有神奇能力的接口的对象，
            // 然后自然而然的就可以使用接口的方法了
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertFalse(userList.isEmpty());
        } finally {
            sqlSession.close();
        }
    }
    /*@Test
    //这个测试用例在书的55页（pdf页码）
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("admin");
            user.setUserPassword("123456");
            user.setUserEmail("admin@qq.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3,4});
            user.setCreateTime(null);
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
        }finally {
            //回滚数据库，关于这一段，在书的55页有详细解释
            sqlSession.rollback();
            sqlSession.close();
        }
    }*/

    @Test //这个是用MyBatis的推荐方式生成的测试用例，区别就在于把整个都放到 try 了
    public void testInsert() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("admin");
            user.setUserPassword("123456");
            user.setUserEmail("admin@qq.com");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3, 4});
            user.setCreateTime(new Date(System.currentTimeMillis()));
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
            sqlSession.commit();
        }
    }
    @Test
    public void testInsert2(){
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("4444");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3, 4});
            user.setCreateTime(new Date(System.currentTimeMillis()));
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
            //这个可以看看58页，关于这个 commit 和 rollback
            sqlSession.commit();
            sqlSession.rollback();
        }
    }
    @Test
    public void testUpdate() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUserName());
            user.setUserName("admin_test");
            user.setUserEmail("testadmin@qq.com");
            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUserName());
        }
    }
    @Test
    public void testDelete() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1045L);
            Assert.assertNotNull(user.getId());
            userMapper.deleteById(1045L);
            sqlSession.commit();
            Assert.assertNull(userMapper.selectById(1045L));
            sqlSession.rollback();
        }
    }
    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        try(SqlSession sqlSession = getSqlSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> userList =
                    userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);
            Assert.assertNotNull(userList);
            Assert.assertFalse(userList.isEmpty());
        }
    }
    @Test
    public void testSelectRolesByUserAndRole(){
        try(SqlSession sqlSession = getSqlSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            SysRole role = new SysRole();
            role.setEnabled(1);
            List<SysRole> userList =
                    userMapper.selectRolesByUserAndRole(user,role);
            Assert.assertNotNull(userList);
            Assert.assertFalse(userList.isEmpty());
        }
    }
    @Test
    public void testSelectByUser(){
        try (SqlSession sqlSession = getSqlSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ad");
            user.setUserEmail("");
            List<SysUser>userList = userMapper.selectByUser(user);
            Assert.assertNotNull(userList);
        }
    }
    @Test
    public void testUpdateByIdSelective(){
        try (SqlSession sqlSession = getSqlSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserEmail("test@test.com");
            int result = userMapper.updateByIdSelective(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@test.com", user.getUserEmail());
        }
    }

    @Test
    public void testInsertList() {
        try (SqlSession sqlSession = getSqlSession();) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);
        }
    }
}
