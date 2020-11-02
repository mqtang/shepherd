package guru.bootstrap.shepherd.po;

import java.util.Date;

/**
 * @author tangcheng
 */
public class BasePO {
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
// 2020/11/1 0:10
