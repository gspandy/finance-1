package com.easyrong.wireless.user.constants;

import com.easyrong.wireless.user.base.BaseErrorInterface;

public enum UserErrorInfo implements BaseErrorInterface {

    USRENAME_NULL("1001", "用户名为空"),
    PASSWORD_NULL("1002", "密码为空"),
    MOBILE_NULL("1003", "电话号码为空"),
    TYPE_NULL("1004", "用户类型为空"),
    USRENAME_PASSWORD_ERROR("10005", "用户名或密码错误"),
    USRENAME_INEXISTENCE("10006", "用户不存在"),
    ;

    UserErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
