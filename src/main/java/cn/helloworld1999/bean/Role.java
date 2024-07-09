package cn.helloworld1999.bean;

import java.util.ArrayList;
import java.util.List;

public class Role {

    private int userId; //用户 userId 原本的已被废弃，现在直接用 userId来绑定，所以不提供get()
    private String roleName;
    final static String[] ROLE_LIST = {"客户","商家","管理员"};

    public int getUserId() { return userId;}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
