package cn.helloworld1999.bean;

public class Role {
    private Integer id; //表id
    private Integer userId; //用户 userId 原本的已被废弃，现在直接用 userId来绑定，所以不提供get()
    private String roleName;
    public final static String[] ROLE_LIST = {"客户","商家","管理员"};

    public Role(Integer userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() { return userId;}

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
