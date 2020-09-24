package guru.bootstrap.shepherd.po;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author tangcheng
 */
@Alias("coreLogonHistoryPO")
public class CoreLogonHistoryPO {
    private Long recId;
    private Long userId;
    private Integer authType;
    private String userAgent;
    private Date logonTime;
    private String logonIp;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public String getLogonIp() {
        return logonIp;
    }

    public void setLogonIp(String logonIp) {
        this.logonIp = logonIp;
    }
}
// 2020/9/24 8:54
