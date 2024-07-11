package cn.helloworld1999.testMybatis;

import cn.helloworld1999.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import cn.helloworld1999.util.MyBatisUtil;

public class test {
    public static void main(String[] args) {
        try(SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.showAllUsers();
        }
    }
}
