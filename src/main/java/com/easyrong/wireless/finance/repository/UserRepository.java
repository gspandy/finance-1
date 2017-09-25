package com.easyrong.wireless.finance.repository;

import com.easyrong.wireless.finance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long > {
}
