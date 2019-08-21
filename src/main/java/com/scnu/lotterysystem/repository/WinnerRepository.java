package com.scnu.lotterysystem.repository;

import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WinnerRepository extends JpaRepository<Winner,Integer> {
    //通过用户查找
    List<Winner> findByUser(User user);
}

