package guru.bootstrap.shepherd.po;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author tangcheng
 */
@Alias("coreUserLogonPO")
public class CoreUserLogonPO {
    private Long recId;
    private Long userId;
    private Integer authType;
    private String authIdentity;
    private String authKey;
    private Integer verified;
    private String lastLogonIp;
    private Date lastLogonTime;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

    public CoreUserLogonPO() {
    }

    public CoreUserLogonPO(Integer authType, String authIdentity) {
        this.authType = authType;
        this.authIdentity = authIdentity;
    }

    public CoreUserLogonPO(Integer authType, String authIdentity, String authKey) {
        this.authType = authType;
        this.authIdentity = authIdentity;
        this.authKey = authKey;
    }

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

    public String getAuthIdentity() {
        return authIdentity;
    }

    public void setAuthIdentity(String authIdentity) {
        this.authIdentity = authIdentity;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
// 2020/9/4 13:29
