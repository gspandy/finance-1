package com.easyrong.wireless.oauthserver.service;

import com.easyrong.wireless.oauthserver.entity.ApiUserEntity;
import com.easyrong.wireless.oauthserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApiUserEntity apiUserEntity = userRepository.findByName(username);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                GrantedAuthority grantedAuthority = (GrantedAuthority) () -> "admin";
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(grantedAuthority);
                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return apiUserEntity.getPassword();
            }

            @Override
            public String getUsername() {
                return apiUserEntity.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}
