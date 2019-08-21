package com.scnu.lotterysystem.repository;

import com.scnu.lotterysystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    //根据用户名查询用户
    User findByUsername(String username);
}
