package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface UserMapper {
    List<User> showAllUsers();

    /**
     * 向数据库中插入一个自动分配id的 User
     * @param user 这个 User 要使用 无 id 的 User 构造方法
     */
    void insertUser(User user);

    /**
     * 通过 账号 查找 User
     * @param user User对象
     * @return User对象 或 NULL
     */
    User selectUserByAccount(User user);

    /**
     * 动态更新 User 并保存到数据库
     * 可以更新的内容有：password、balance、phoneNum
     * @param user 待修改的 User 对象
     * @return 返回一个修改后的 User 对象
     */
    int updateUserSelective(User user);

    /**
     * 动态SQL 支持使用账号名模糊查询
     * @param user 可用 userId、account、balance、user_phone_num
     * @return 返回符合条件的 User 列表
     */
    List<User> selectUserSelective(User user);

    User selectUserByUserId(User user);

    int deleteUserByUserId(User user);
}
