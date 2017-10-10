package com.easyrong.wireless.finance.entity;

import com.alibaba.fastjson.JSON;

/**
 * 新三板数据
 */
public class NeeqRspEntity {
    private String code;
    private String closePrice;   //收盘价
    private double allValue; //总股本万元
    private String name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice + "万元";
    }

    public double getAllValue() {
        return allValue;
    }

    public void setAllValue(double allValue) {
        this.allValue = allValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
