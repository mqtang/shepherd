package guru.bootstrap.shepherd.po.zk;

import guru.bootstrap.shepherd.po.BasePO;

import java.util.Date;

/**
 * @author tangcheng
 */
public class CoreZkConnPO extends BasePO {
    private Long connId;
    private Long userId;
    private Long serverId;
    private Long groupId;

    private String connName;
    private String connString;
    private String showTitle;
    private String namespace;
    private String description;
    private Integer retryPolicy;
    private Integer connTimeout;
    private Integer sessionTimeout;

    private String colorConfig;
    private Integer showOrder;
    private Integer isStickTop;
    private Date stickTopTime;

    private Date lastConnTime;

    public Long getConnId() {
        return connId;
    }

    public void setConnId(Long connId) {
        this.connId = connId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getConnName() {
        return connName;
    }

    public void setConnName(String connName) {
        this.connName = connName;
    }

    public String getConnString() {
        return connString;
    }

    public void setConnString(String connString) {
        this.connString = connString;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(Integer retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public Integer getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(Integer connTimeout) {
        this.connTimeout = connTimeout;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getColorConfig() {
        return colorConfig;
    }

    public void setColorConfig(String colorConfig) {
        this.colorConfig = colorConfig;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public Integer getIsStickTop() {
        return isStickTop;
    }

    public void setIsStickTop(Integer isStickTop) {
        this.isStickTop = isStickTop;
    }

    public Date getStickTopTime() {
        return stickTopTime;
    }

    public void setStickTopTime(Date stickTopTime) {
        this.stickTopTime = stickTopTime;
    }

    public Date getLastConnTime() {
        return lastConnTime;
    }

    public void setLastConnTime(Date lastConnTime) {
        this.lastConnTime = lastConnTime;
    }
}
// 2020/9/5 9:19
