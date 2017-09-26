package com.easyrong.wireless.finance.service.impl;

import com.easyrong.wireless.finance.entity.UserEntity;
import com.easyrong.wireless.finance.repository.UserRepository;
import com.easyrong.wireless.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean verifyUser(UserEntity user) {
        if (userRepository.findByNameAndPassword(user.getName(), user.getPassword()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean registerUser(UserEntity user) {
        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
