package com.scnu.lotterysystem.service;

import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.entity.Winner;

import java.util.List;

public interface WinnerService {

    //查找全部获奖者
    List<Winner> findAll();

    //通过用户查找获奖者
    List<Winner> findByUser(User user);

    //保存获奖者
    Winner saveWinner(Winner winner);

    //删除获奖者
    void removeWinner(Integer id);
}
