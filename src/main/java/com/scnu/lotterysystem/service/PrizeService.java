package com.scnu.lotterysystem.service;


import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.User;

import java.util.List;

//奖品的service接口
public interface PrizeService {

    //查找全部用户
    List<Prize> findAll();

    //通过用户名查找所属奖品
    List<Prize> findByUser(User user);

    //保存奖品
    Prize savePrize(Prize prize);

    //修改奖品
    Prize updatePrize(Prize prize);

     //通过id查找奖品
    Prize getPrizeById(Integer id);

    //通过id删除奖品
    void removePrize(Integer id);

    //奖品数量减一操作（当被抽到）
    Prize minusPrize(Prize prize);

    //根据奖品名查找奖品
    Prize findByPrizeName(String prizeName);
}
