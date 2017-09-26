package com.easyrong.wireless.finance.repository;

import com.easyrong.wireless.finance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long > {
    public List<UserEntity> findByName(String name);

    public List<UserEntity> findByNameAndPassword(String name, String password);

    @Query("from UserEntity u where u.name=:name")
    public List<UserEntity> findUser(@Param("name") String name);
}
