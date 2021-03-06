package com.easyrong.wireless.oauthserver.entity;

import com.alibaba.fastjson.JSON;
import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "api_user")
public class ApiUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "bigint(20) COMMENT '主键'")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "name", unique = true, columnDefinition = "varchar(250) COMMENT '用户名称'")
    private String name;

    @Column(nullable = false, name = "password", columnDefinition = "varchar(50) COMMENT '密码'")
    private String password;

    @Column(nullable = false, name = "createTime", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false, name = "updateTime", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

    @ManyToMany(targetEntity = ApiRoleEntity.class, fetch = FetchType.EAGER)
    @BatchSize(size = 20)
    private Set<ApiRoleEntity> roles = new HashSet<>();

    @Transient
    private Set<GrantedAuthority> authorities = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<ApiRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<ApiRoleEntity> roles) {
        this.roles = roles;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(ApiRoleEntity role : this.roles){
            for(ApiAuthorityEntity authority : role.getAuthoritys()){
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
            }
        }

        return grantedAuthorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
