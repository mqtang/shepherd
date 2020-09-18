package guru.bootstrap.shepherd.po;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author tangcheng
 */
@Alias("coreUserPO")
public class CoreUserPO {
    private Long userId;
    private String username;
    private String nickname;
    private Integer gender;
    private String identityNumber;
    private Long avatarId;
    private Date birthday;
    private Date birthdayLunar;
    private Integer birthdayType;
    private Integer constellation;
    private String memberId;
    private String email;
    private String cellphone;
    private String website;
    private Integer accountType;
    private Integer accountStatus;
    private String registerIp;
    private Integer registerType;
    private Integer completed;

    private Integer deleted;
    private Date createTime;
    private Date updateTime;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthdayLunar() {
        return birthdayLunar;
    }

    public void setBirthdayLunar(Date birthdayLunar) {
        this.birthdayLunar = birthdayLunar;
    }

    public Integer getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(Integer birthdayType) {
        this.birthdayType = birthdayType;
    }

    public Integer getConstellation() {
        return constellation;
    }

    public void setConstellation(Integer constellation) {
        this.constellation = constellation;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
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
// 2020/9/4 13:28
