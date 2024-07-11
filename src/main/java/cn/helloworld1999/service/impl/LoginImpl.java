package cn.helloworld1999.service.impl;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.mapper.RoleMapper;
import cn.helloworld1999.mapper.UserMapper;
import cn.helloworld1999.service.Login;
import cn.helloworld1999.util.MyBatisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import static cn.helloworld1999.util.GetMapper.getUserMapper;
import static cn.helloworld1999.util.MyBatisUtil.getSqlSession;

public class LoginImpl implements Login {

    /**
     * 登录功能
     *
     * @param loginUser Controller 传过来的 User 对象的 userId
     * @return -2 密码不对，-1 账号都不存在
     */
    @Override
    public Integer login(User loginUser) {
        User user = getUserMapper().selectUserByAccount(loginUser);
        if (user != null) {
            if (user.getPassword().equals(loginUser.getPassword())) {
                return user.getUserId();
            } else {
                return -2;
            }
        }
        return -1;
    }

    @Override
    public Integer register(User loginUser) {
        if ((login(loginUser) != null) && (login(loginUser) != -1)) {
            SafeImpl safe = new SafeImpl();
            safe.ReCaptcha(new User()); // 这是一个没写完的方法，先来占一下位置，被卡在腾讯云的短信签名了
            //TODO 写到这里是一半了，这里要做验证码的获取和校验以及计时
            //------ 施 工 中 ------
            //假设通过了

        }
        return null;
    }

    @Override
    public Integer findPassword(User loginUser) {
        return null;
    }
}
