package com.easyrong.wireless.oauthserver.entity;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "api_role")
public class ApiRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "bigint(20) COMMENT '主键'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "name", unique = true, columnDefinition = "varchar(250) COMMENT '角色名称'")
    private String name;

    @Column(nullable = false, name = "value", columnDefinition = "varchar(50) COMMENT '角色值'")
    private String value;

    @CreationTimestamp
    @Column(nullable = false, name = "createTime", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    @UpdateTimestamp
    @Column(nullable = false, name = "updateTime", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

    @ManyToMany(targetEntity = ApiAuthorityEntity.class, fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    private Set<ApiAuthorityEntity> authoritys = new HashSet<>();

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

    public Set<ApiAuthorityEntity> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(Set<ApiAuthorityEntity> authoritys) {
        this.authoritys = authoritys;
    }
}
