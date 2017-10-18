package com.easyrong.wireless.oauthserver.service;

import com.easyrong.wireless.oauthserver.entity.ApiUserEntity;
import com.easyrong.wireless.oauthserver.repository.ApiUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<ApiUserEntity> realUser = apiUserRepository.findOneWithRolesByName(name.toLowerCase());

        return realUser.map(user -> {
            Set<GrantedAuthority> grantedAuthorities = user.getAuthorities();
            return new User(user.getName(), user.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("用户" + name + "不存在!"));
    }
}
