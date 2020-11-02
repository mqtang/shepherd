package guru.bootstrap.shepherd.po.zk;

import guru.bootstrap.shepherd.po.BasePO;

import java.util.Date;

/**
 * @author tangcheng
 */
public class CoreZkGroupPO extends BasePO {
    private Long groupId;
    private Long userId;
    private String groupName;
    private String description;
    private String colorConfig;

    private Integer showOrder;
    private Integer isStickTop;
    private Date stickTopTime;

    private Integer isSafeMode;
    private String safeModePwd;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColorConfig() {
        return colorConfig;
    }

    public void setColorConfig(String colorConfig) {
        this.colorConfig = colorConfig;
    }

    public Integer getIsSafeMode() {
        return isSafeMode;
    }

    public void setIsSafeMode(Integer isSafeMode) {
        this.isSafeMode = isSafeMode;
    }

    public String getSafeModePwd() {
        return safeModePwd;
    }

    public void setSafeModePwd(String safeModePwd) {
        this.safeModePwd = safeModePwd;
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
}
// 2020/10/31 21:59
