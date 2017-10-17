package com.easyrong.wireless.oauthserver.repository;

import com.easyrong.wireless.oauthserver.entity.ApiUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApiUserEntity, Long> {
    ApiUserEntity findByName(String name);
}
