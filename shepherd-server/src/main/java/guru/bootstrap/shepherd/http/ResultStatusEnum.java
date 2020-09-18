package guru.bootstrap.shepherd.http;

public enum ResultStatusEnum {
    OK(0, "ok"), NEED_LOGIN(99999, "未登录");

    private int code;
    private String desc;

    ResultStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
