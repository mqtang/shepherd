package guru.bootstrap.shepherd.http;

public enum ResultStatusEnum {
    OK(0, "成功", "ok"),
    UNKNOWN_ERROR(50505, "服务正忙, 请稍后再试", "try again later"),
    NEED_LOGIN(99999, "未登录", "need to login");

    private int code;
    private String desc;
    private String descEn;

    ResultStatusEnum(int code, String desc, String descEn) {
        this.code = code;
        this.desc = desc;
        this.descEn = descEn;
    }

    public int getCode() {
        return code;
    }

    public String getCodeWithPrefix() {
        return "" + code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescEn() {
        return descEn;
    }
}
