package cn.helloworld1999.easyTest;

import cn.helloworld1999.bean.SysRole;
import cn.helloworld1999.bean.SysUser;
import cn.helloworld1999.mapper.UserMapper;
import cn.helloworld1999.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class testUserMapper {

    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<SysRole> u = userMapper.selectRolesByUserId(1L);
        System.out.println(u.toString());
    }
}
