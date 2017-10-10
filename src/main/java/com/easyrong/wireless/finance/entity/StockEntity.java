package com.easyrong.wireless.finance.entity;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="stock")
public class StockEntity {
    List<String> ashares = new ArrayList<>();
    List<String> neeq = new ArrayList<>();

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public List<String> getAshares() {
        return ashares;
    }

    public void setAshares(List<String> ashares) {
        this.ashares = ashares;
    }

    public List<String> getNeeq() {
        return neeq;
    }

    public void setNeeq(List<String> neeq) {
        this.neeq = neeq;
    }
}
