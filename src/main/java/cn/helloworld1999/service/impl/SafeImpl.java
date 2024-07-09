package cn.helloworld1999.service.impl;

import cn.helloworld1999.service.Safe;

public class SafeImpl implements Safe {
    @Override
    public boolean ReCaptcha() {
        return false;
    }
}
