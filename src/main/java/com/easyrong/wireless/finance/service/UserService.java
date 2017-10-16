package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.entity.UserEntity;

public interface UserService {
    boolean userLogin(UserEntity user);
    boolean userRegister(UserEntity user);
}
