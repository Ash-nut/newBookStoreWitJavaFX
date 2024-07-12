package cn.helloworld1999.service.impl;

import cn.helloworld1999.bean.User;
import cn.helloworld1999.service.Safe;

import java.util.Random;

public class SafeImpl implements Safe {
    @Override
    public String ReCaptcha() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 生成一个六位数的随机数
        return String.valueOf(code);
    }
}
