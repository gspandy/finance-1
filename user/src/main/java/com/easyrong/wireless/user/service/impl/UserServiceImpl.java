package com.easyrong.wireless.user.service.impl;

import com.easyrong.wireless.user.entity.UserEntity;
import com.easyrong.wireless.user.repository.UserRepository;
import com.easyrong.wireless.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<UserEntity> findListByPage(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public Iterable<UserEntity> findListByTime(Timestamp regTime, Pageable pageable) {
        return userRepository.findAll(
                (root, criteriaQuery, criteriaBuilder) -> {
                    if (null != regTime) {
                        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("updateTime").as(Timestamp.class), regTime));
                    }
                    return null;
                }, pageable).getContent();
    }

    @Override
    public UserEntity findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserEntity findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserEntity findByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Iterable<UserEntity> save(Iterable<UserEntity> list) {
        return userRepository.save(list);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Iterable<UserEntity> update(Iterable<UserEntity> list) {
        return userRepository.save(list);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
