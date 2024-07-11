package cn.helloworld1999.util;

import cn.helloworld1999.mapper.*;
import org.apache.ibatis.session.SqlSession;

import static cn.helloworld1999.util.MyBatisUtil.getSqlSession;

public class GetMapper {
    private static SqlSession sqlSession = getSqlSession();

    static public UserMapper getUserMapper() {
        return sqlSession.getMapper(UserMapper.class);
    }

    static public RoleMapper getRoleMapper() {
        return sqlSession.getMapper(RoleMapper.class);
    }

    static public OrderMapper getOrderMapper() {
        return sqlSession.getMapper(OrderMapper.class);
    }

    static public BookMapper getBookMapper() {
        return sqlSession.getMapper(BookMapper.class);
    }

    static public OrderSubpageMapper getOrderSubpageMapper() {
        return sqlSession.getMapper(OrderSubpageMapper.class);
    }

    // 新增手动提交事务的方法
    public static void commit() {
        sqlSession.commit();
    }

    public static void rollback() {
        sqlSession.rollback();
    }

    public static void close() {
        sqlSession.close();
    }
}
