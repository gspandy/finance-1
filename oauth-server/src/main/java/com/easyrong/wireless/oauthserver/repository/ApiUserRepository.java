package com.easyrong.wireless.oauthserver.repository;

import com.easyrong.wireless.oauthserver.entity.ApiUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUserRepository extends JpaRepository<ApiUserEntity, Long> {
    Optional<ApiUserEntity> findOneWithRolesByName(String name);
}
