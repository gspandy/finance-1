package com.easyrong.wireless.user.repository;

import com.easyrong.wireless.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByName(String name);
    UserEntity findByNameAndPassword(String name, String password);
}
