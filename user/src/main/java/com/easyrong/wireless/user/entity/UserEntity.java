package com.easyrong.wireless.user.entity;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "finance_user")
public class UserEntity {
    @Id
    @Column(name = "id", columnDefinition = "bigint(20) COMMENT '主键'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "mobile", unique = true, columnDefinition = "bigint(20) COMMENT '电话号码'")
    private long mobile;

    @Column(nullable = false, name = "name", unique = true, columnDefinition = "varchar(250) COMMENT '账户名称'")
    private String name;

    @Column(nullable = false, name = "password", columnDefinition = "varchar(50) COMMENT '密码'")
    private String password = "xlh123456";

    @Column(nullable = false, name = "type", columnDefinition = "int(11) COMMENT '账户类型 1:金融办 2:金融机构 3:企业'")
    private int type;

    @Column(name = "duty", columnDefinition = "varchar(250) COMMENT '账户职务'")
    private String duty;

    @CreationTimestamp
    @Column(nullable = false, name = "regTime", columnDefinition = "DATETIME COMMENT '注册时间'")
    private Timestamp regTime;

    @UpdateTimestamp
    @Column(nullable = false, name = "lastLoginTime", columnDefinition = "DATETIME COMMENT '最近登录时间'")
    private Timestamp lastLoginTime;

    @CreationTimestamp
    @Column(nullable = false, name = "createTime", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(nullable = false, name = "updateTime", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

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

    public String getduty() {
        return duty;
    }

    public void setduty(String duty) {
        this.duty = duty;
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
