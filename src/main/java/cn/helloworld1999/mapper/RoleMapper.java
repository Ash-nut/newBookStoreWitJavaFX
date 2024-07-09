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
     * 搜索某人所拥有的全部角色 或 某个人的某个角色
     * 应用场景：1.登录后查询 User 的 Role 2.可以拿到某人的某个角色的对象，以便于后续操作
     * @param role 可以单 userId 构造，或 全参构造 效果即1、2
     * @return List<Role>
     */
    List<Role> selectSomeRole(Role role);

    /**
     * 根据传入的 对象-角色 列表，删除对象的角色
     * @param roles 这个列表百分百是从 RoleMapper 的 select 方法得到的
     * @return 影响行数
     */
    int deleteRole(List<Role> roles);
}
