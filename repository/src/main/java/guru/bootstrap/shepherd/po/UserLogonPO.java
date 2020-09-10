package guru.bootstrap.shepherd.po;

import java.util.Date;

/**
 * @author tangcheng
 */
public class UserLogonPO {
    private Long userId;
    private String logonUsername;
    private String password;
    private Integer ipAddress;

    private Date createTime;
    private Date updateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogonUsername() {
        return logonUsername;
    }

    public void setLogonUsername(String logonUsername) {
        this.logonUsername = logonUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Integer ipAddress) {
        this.ipAddress = ipAddress;
    }
}
// 2020/9/4 13:29
