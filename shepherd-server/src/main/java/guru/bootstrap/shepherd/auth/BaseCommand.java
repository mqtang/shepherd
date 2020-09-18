package guru.bootstrap.shepherd.auth;

import java.util.Date;

/**
 * @author tangcheng
 */
public class BaseCommand {
    private Long userId;
    private String username;
    private Date lvt;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLvt() {
        return lvt;
    }

    public void setLvt(Date lvt) {
        this.lvt = lvt;
    }

    public boolean isLogin() {
        return this.userId != null;
    }

}
// 2020/9/16 22:11
