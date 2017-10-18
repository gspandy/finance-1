package com.easyrong.wireless.oauthserver.repository;

import com.easyrong.wireless.oauthserver.entity.ApiAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAuthorityRepository extends JpaRepository<ApiAuthorityEntity, Long> {
}
