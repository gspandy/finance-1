package com.easyrong.wireless.user.constants;

public enum UserTypeEnum {
    OFFICE((byte) 1, "金融办"), INSTITUTION((byte) 2, "金融机构");

    private Byte type;

    private String name;

    UserTypeEnum(Byte type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getName(Byte type) {
        for (UserTypeEnum u : UserTypeEnum.values()) {
            if (u.getType() == type) {
                return u.name;
            }
        }
        return null;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
