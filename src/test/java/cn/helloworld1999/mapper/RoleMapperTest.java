package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.Role;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void testSelectAllUserRole() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<Role> strRoleList = Arrays.asList(
                    new Role(2, "客户"),
                    new Role(2, "管理员"),
                    new Role(1, "商家"),
                    new Role(3, "商家"),
                    new Role(4, "客户"));
            List<Role> roles = roleMapper.selectAllUserRole();
            for (int i =0, len = roles.size(); i < len; i++) {
                Assert.assertEquals(strRoleList.get(i).toString(), roles.get(i).toString());
            }
        }

    }

    @Test
    public void selectRoleSelective() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            List<Role>ro1 = roleMapper.selectSomeRole(new Role(1,null));
            Assert.assertEquals(1,ro1.size());

            List<Role>ro2 = roleMapper.selectSomeRole(new Role(2,null));
            Assert.assertEquals(2,ro2.size());
            List<Role>ro3 = roleMapper.selectSomeRole(new Role(null,null));
            Assert.assertEquals(2,ro2.size());








        }
    }
}
