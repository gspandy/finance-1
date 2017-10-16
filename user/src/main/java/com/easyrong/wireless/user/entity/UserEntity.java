package com.easyrong.wireless.user.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "finance_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "mobile")
    private long mobile;

    @Column(nullable = false, name = "name")
    private String name;


    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "type")
    private int type;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "regTime")
    private Timestamp regTime;

    @Column(name = "lastLoginTime")
    private Timestamp lastLoginTime;

    @Column(nullable = false, name = "createTime")
    private Timestamp createTime;

    @Column(nullable = false, name = "updateTime")
    private Timestamp updateTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
