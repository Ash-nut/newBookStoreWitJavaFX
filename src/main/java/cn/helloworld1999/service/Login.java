package cn.helloworld1999.service;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;

public interface Login {
    /**
     * 拿到 UserMapper
     * @return
     */
    UserMapper getUserMapper();

    /**
     * 登录判断

     * @return 返回 数据库内的 用户id
     */
    Integer login(User loginUser);

    /**
     * 注册账户
     * @return 返回 数据库内的 用户id
     */
    Integer register(User loginUser);

    /**
     * 重置密码
     * @return 返回 数据库内的 用户id
     */
    Integer findPassword(User loginUser);





}
