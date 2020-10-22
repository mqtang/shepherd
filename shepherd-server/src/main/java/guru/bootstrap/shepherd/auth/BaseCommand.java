package guru.bootstrap.shepherd.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author tangcheng
 */
public class BaseCommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public BaseCommand() {
    }

    public BaseCommand(HttpServletRequest request, HttpServletResponse response) {
        assert request != null;
        assert response != null;
        this.request = request;
        this.response = response;
    }

    private Long userId;
    private String username;

    private String loginStatusToken;
    private String _csrf_token;
    private Date _lvt;
    private String _lan;
    private int _version;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

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

    public String get_csrf_token() {
        return _csrf_token;
    }

    public void set_csrf_token(String _csrf_token) {
        this._csrf_token = _csrf_token;
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

    public boolean isNotLogin() {
        return !isLogin();
    }

}
// 2020/9/16 22:11
