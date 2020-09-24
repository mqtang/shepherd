package guru.bootstrap.shepherd.service.user;

import java.util.Date;

/**
 * @author tangcheng
 */
public class UserServiceDTO {
    // 用户id
    private Long userId;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 鉴权类型
    private Integer authType;
    // 鉴权ip
    private String authIp;

    private String lastLogonIp;
    private Date lastLogonTime;

    private String logonIp;
    private Date logonTime;
    private String userAgent;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getAuthIp() {
        return authIp;
    }

    public void setAuthIp(String authIp) {
        this.authIp = authIp;
    }

    public String getLastLogonIp() {
        return lastLogonIp;
    }

    public void setLastLogonIp(String lastLogonIp) {
        this.lastLogonIp = lastLogonIp;
    }

    public Date getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(Date lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getLogonIp() {
        return logonIp;
    }

    public void setLogonIp(String logonIp) {
        this.logonIp = logonIp;
    }

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
// 2020/9/16 11:21
