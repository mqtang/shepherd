package guru.bootstrap.shepherd.service.user;

public enum UserStatusEnum {
    OK(0, "注册成功", "reg success"),
    MEMBER_ID_EXISTS(2000, "用户名已存在", "member id already exists");

    private static final String PREFIX = "U";

    private int code;
    private String desc;
    private String descEn;

    UserStatusEnum(int code, String desc, String descEn) {
        this.code = code;
        this.desc = desc;
        this.descEn = descEn;
    }

    public int getCode() {
        return code;
    }

    public String getCodeWithPrefix() {
        return PREFIX + code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescEn() {
        return descEn;
    }
}
