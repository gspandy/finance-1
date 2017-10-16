package com.easyrong.wireless.user.service;


import com.easyrong.wireless.user.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

public interface UserService {
    Iterable<UserEntity> findListByPage(Pageable pageable);

    Iterable<UserEntity> findListByTime(Timestamp updateTime, Pageable pageable);

    UserEntity findById(int id);

    UserEntity findByName(String name);

    UserEntity findByNameAndPassword(String name, String password);

    UserEntity save(UserEntity userEntity);

    Iterable<UserEntity> save(Iterable<UserEntity> list);

    UserEntity update(UserEntity userEntity);

    Iterable<UserEntity> update(Iterable<UserEntity> list);

    void delete(int id);
}
