package com.easyrong.wireless.oauthserver.repository;

import com.easyrong.wireless.oauthserver.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByName(String name);
    UserEntity findByNameAndPassword(String name, String password);
}
