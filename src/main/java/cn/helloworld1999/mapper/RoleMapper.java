package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.SysRole;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
    @Select("SELECT id, role_name AS roleName, enabled, create_by AS createBy, create_time AS createTime " +
            "FROM sys_role " +
            "WHERE id = #{id}")
    SysRole selectById(Long id);
}