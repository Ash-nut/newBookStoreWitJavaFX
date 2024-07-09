package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.Role;
import cn.helloworld1999.bean.User;

import java.util.List;

public interface RoleMapper {
    /**
     * 得到角色总表
     * @return 返回 所有用户 的 所有角色
     */
    List<Role> selectAllUserRole();

    /**
     * 模糊搜索 Role
     * 应用场景：1.登录后查询 User 的 Role；2.筛选出所有的 Role UserId
     * @param role 一个以任意属性构造的非空 Role
     * @return 数值符合 Role 中非空属性的 List<Role>
     */
    List<Role> selectRoleSelective(Role role);
}
