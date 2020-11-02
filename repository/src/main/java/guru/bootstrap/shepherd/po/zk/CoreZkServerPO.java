package guru.bootstrap.shepherd.po.zk;

import guru.bootstrap.shepherd.po.BasePO;

import java.util.Date;

/**
 * @author tangcheng
 */
public class CoreZkServerPO extends BasePO {
    private Long serverId;
    private Long userId;
    private Long groupId;

    private String serverName;
    private String serverHost;
    private String showTitle;
    private String description;
    private String colorConfig;

    private Integer showOrder;
    private Integer isStickTop;
    private Date stickTopTime;

    private Date lastConnTime;

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getColorConfig() {
        return colorConfig;
    }

    public void setColorConfig(String colorConfig) {
        this.colorConfig = colorConfig;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
// 2020/9/4 13:40
