package com.easyrong.wireless.ribbonclient.service;

import com.easyrong.wireless.ribbonclient.base.BaseErrorInfo;
import com.easyrong.wireless.ribbonclient.base.BaseRspEntity;
import com.easyrong.wireless.ribbonclient.entity.UserEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "userLoginFallback")
    public Object userLogin(UserEntity userEntity) {
        return restTemplate.postForEntity("http://FINANCE-USER/api/login", userEntity, Object.class).getBody();
    }

    public Object userLoginFallback() {
        return new BaseRspEntity(BaseErrorInfo.SEVICE_ERROR);
    }

    @HystrixCommand(fallbackMethod = "userRegisterFallback")
    public Object userRegister(UserEntity userEntity) {
        return restTemplate.postForEntity("http://FINANCE-USER/api/register", userEntity, Object.class).getBody();
    }

    public Object userRegisterFallback() {
        return new BaseRspEntity(BaseErrorInfo.SEVICE_ERROR);
    }
}
