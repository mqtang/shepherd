package guru.bootstrap.shepherd.auth;

import java.util.Date;

/**
 * @author tangcheng
 */
public class BaseCommand {
    private Long userId;
    private String username;

    private String loginStatusToken;
    private Date _lvt;
    private String _lan;
    private int _version;

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

    public String getLoginStatusToken() {
        return loginStatusToken;
    }

    public void setLoginStatusToken(String loginStatusToken) {
        this.loginStatusToken = loginStatusToken;
    }

    public Date get_lvt() {
        return _lvt;
    }

    public void set_lvt(Date _lvt) {
        this._lvt = _lvt;
    }

    public int get_version() {
        return _version;
    }

    public void set_version(int _version) {
        this._version = _version;
    }

    public String get_lan() {
        return _lan;
    }

    public void set_lan(String _lan) {
        this._lan = _lan;
    }

    public boolean isLogin() {
        return this.userId != null;
    }

}
// 2020/9/16 22:11
