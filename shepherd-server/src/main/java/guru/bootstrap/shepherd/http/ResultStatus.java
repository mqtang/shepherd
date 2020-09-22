package guru.bootstrap.shepherd.http;

import guru.bootstrap.shepherd.service.user.UserStatusEnum;

import java.util.Date;

/**
 * @author tangcheng
 */
public class ResultStatus {
    private String sta;
    private String msg;
    private Date time = new Date();

    public static ResultStatus newStatus(ResultStatusEnum statusEnum) {
        return new ResultStatus(statusEnum.getCode() + "", statusEnum.getDesc());
    }

    public static ResultStatus newStatus(UserStatusEnum statusEnum) {
        return new ResultStatus(statusEnum.getCodeWithPrefix() + "", statusEnum.getDesc());
    }

    public ResultStatus() {
    }

    public ResultStatus(String sta, String msg) {
        this.sta = sta;
        this.msg = msg;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
// 2020/9/16 20:00
