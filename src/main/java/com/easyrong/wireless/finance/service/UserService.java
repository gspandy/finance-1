package com.easyrong.wireless.finance.service;

import com.easyrong.wireless.finance.entity.UserEntity;

public interface UserService {
    boolean verifyUser(UserEntity user);
    String registerUser(UserEntity user);
}
