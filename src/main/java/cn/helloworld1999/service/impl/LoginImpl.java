package cn.helloworld1999.service.impl;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.mapper.UserMapper;
import cn.helloworld1999.service.Login;
import cn.helloworld1999.service.Safe;
import org.apache.ibatis.session.SqlSession;

import static cn.helloworld1999.util.MyBatisUtil.getSqlSession;

public class LoginImpl implements Login {

    /**
     * 拿到 UserMapper
     *
     * @return
     */
    @Override
    public UserMapper getUserMapper() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper;
        }
    }

    /**
     * 登录功能
     *
     * @param loginUser Controller 传过来的 User 对象
     * @return != -1 & null : 用户名 & 密码 true；== -1 用户名 true, 密码 false；== null 用户不存在
     */
    @Override
    public Integer login(User loginUser) {
        User user = getUserMapper().selectUserByAccount(loginUser);
        if (user != null) {
            if (user.getPassword().equals(loginUser.getPassword())) {
                return user.getUserId();
            } else {
                return -1;
            }
        }
        return null;
    }

    @Override
    public Integer register(User loginUser) {
        if ((login(loginUser) != null) && (login(loginUser) != -1)) {
            SafeImpl safe = new SafeImpl();
            safe.ReCaptcha(); // 这是一个没写完的方法，先来占一下位置，被卡在腾讯云的短信签名了
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
