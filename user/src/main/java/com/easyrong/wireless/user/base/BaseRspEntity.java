package com.easyrong.wireless.user.base;

import com.alibaba.fastjson.JSON;

public class BaseRspEntity {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应结果
     */
    private Object result;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public BaseRspEntity(BaseErrorInterface baseErrorInterface) {
        this.code = baseErrorInterface.getCode();
        this.message = baseErrorInterface.getMessage();
    }

    public BaseRspEntity(Object result) {
        this.code = BaseErrorInfo.SUCCESS.getCode();
        this.message = BaseErrorInfo.SUCCESS.getMessage();
        this.result = result;
    }

    public String getCode() {
        return code;
    }
    public void setCode(BaseErrorInfo code) {
        this.code = code.getCode();
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
