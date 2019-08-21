package com.scnu.lotterysystem.repository;

import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize,Integer> {
    //通过奖品名字查询奖品
    Prize findByPrizeName(String prizeName);

    //通过用户名查找所属奖品
    List<Prize> findByUser(User user);
}
