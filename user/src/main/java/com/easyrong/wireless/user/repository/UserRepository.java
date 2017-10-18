package com.easyrong.wireless.user.repository;

import com.easyrong.wireless.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByName(String name);

    UserEntity findByNameAndPassword(String name, String password);
}
