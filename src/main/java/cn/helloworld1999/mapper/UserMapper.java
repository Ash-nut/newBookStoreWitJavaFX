package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.SysRole;
import cn.helloworld1999.bean.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userId);

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    //67页，现在可以通过带入两个实例来进行查询了
    List<SysRole> selectRolesByUserAndRole(
            @Param("user") SysUser user,
            @Param("role") SysRole role);
    List<SysUser> selectByUser(SysUser user);
    int updateByIdSelective(SysUser sysUser);
    SysUser selectByIdOrUserName(SysUser sysUser);
    List<SysUser> selectByIdList(List<Long> idList);
    int insertList(List<SysUser>userList);
}
