package guru.bootstrap.shepherd.service.user;

public enum RegisterTypeEnum {
    MEMBER_ID(0, "member"), EMAIL(1, "email"), CELLPHONE(2, "cellphone");

    private Integer typeKey;
    private String typeName;

    RegisterTypeEnum(Integer typeKey, String typeName) {
        this.typeKey = typeKey;
        this.typeName = typeName;
    }

    public static Integer getTypeKeyByName(String typeName) {
        for (RegisterTypeEnum typeEnum : RegisterTypeEnum.values()) {
            if (typeEnum.typeName.equalsIgnoreCase(typeName)) {
                return typeEnum.typeKey;
            }
        }
        return null;
    }

    public Integer getTypeKey() {
        return typeKey;
    }

    public String getTypeName() {
        return typeName;
    }
}
