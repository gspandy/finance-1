package com.easyrong.wireless.oauthserver.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity()
@Table(name = "api_authority")
public class ApiAuthorityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "bigint(20) COMMENT '主键'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "name", unique = true, columnDefinition = "varchar(250) COMMENT '权限名称'")
    private String name;

    @Column(nullable = false, name = "value", columnDefinition = "varchar(50) COMMENT '权限值'")
    private String value;

    @Column(nullable = false, name = "createTime", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime;

    @Column(nullable = false, name = "updateTime", columnDefinition = "DATETIME COMMENT '修改时间'")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
