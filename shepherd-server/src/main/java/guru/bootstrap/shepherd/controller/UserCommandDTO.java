package guru.bootstrap.shepherd.controller;

import guru.bootstrap.shepherd.service.user.RegisterTypeEnum;

/**
 * @author tangcheng
 */
public class UserCommandDTO {
    private String username;
    private String password;
    private String method;

    public Integer getRegisterTypeKey() {
        Integer key =
                RegisterTypeEnum.getTypeKeyByName(this.method);
        if (key != null) {
            return key;
        }
        return RegisterTypeEnum.MEMBER_ID.getTypeKey();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
// 2020/9/16 10:59
