package com.easyrong.wireless.finance.entity;

import com.alibaba.fastjson.JSON;

public class StockRspEntity {
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

    public String getAllValue() {
        return allValue;
    }

    public void setAllValue(String allValue) {
        this.allValue = allValue;
    }

    public String getClosePrice() {
        return closePrice + "亿元";
    }

    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String code;
    private String allValue; //单个股票总市值
    private String closePrice; //单个股票收盘价
    private String name;
}
