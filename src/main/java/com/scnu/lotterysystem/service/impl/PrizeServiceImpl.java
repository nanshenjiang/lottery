package com.scnu.lotterysystem.service.impl;

import com.scnu.lotterysystem.entity.Prize;
import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.repository.PrizeRepository;
import com.scnu.lotterysystem.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@CacheConfig設置cache
@Service
@CacheConfig(cacheNames = "prize",cacheManager = "prizeCacheManager")
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeRepository prizeRepository;

    @Override
    @Transactional
    public List<Prize> findAll() {
        return prizeRepository.findAll();
    }

    //通过用户名查找所属奖品
    @Override
    public List<Prize> findByUser(User user) {
        return prizeRepository.findByUser(user);
    }

    /* *
    * 保存prize，其中最初设置为审核未通过
    * */
    @CachePut(key="#result.id")
    @Override
    @Transactional
    public Prize savePrize(Prize prize) {
        try{
            prizeRepository.save(prize);
        }catch (Exception e){
            throw new RuntimeException("Add Prize Error:"+e.getMessage());
        }
        return prize;
    }

    /* *
     * 修改prize，由于修改，则审核通过
     * */
    @CachePut(key="#result.id")
    @Override
    @Transactional
    public Prize updatePrize(Prize prize) {
        try{
            prizeRepository.save(prize);
        }catch (Exception e){
            throw new RuntimeException("Add Prize Error:"+e.getMessage());
        }
        return prize;
    }

    @Cacheable(key = "#id")
    @Override
    @Transactional
    public Prize getPrizeById(Integer id) {
        return prizeRepository.findOne(id);
    }

    @CacheEvict(key="#id")
    @Override
    @Transactional
    public void removePrize(Integer id) {
        prizeRepository.delete(id);
    }

    /* *
    * 当获奖的时候奖品数量减一
    * */
    @Caching(
            cacheable = {
                    @Cacheable(key="#prize.id")
            },
            put ={
                    @CachePut(key="#result.id")
            }
    )
    @Override
    @Transactional
    public Prize minusPrize(Prize prize) {
        try {
            Integer num = prize.getPrizeNum();
            prize.setPrizeNum(num - 1);
            prizeRepository.save(prize);
        }catch (Exception e){
            throw new RuntimeException("Minus Prize Error: "+e.getMessage());
        }
        return prize;
    }

    //通过奖品名查询奖品
    @Caching(
            cacheable = {
                    @Cacheable(key="#prizeName")
            },
            put ={
                     @CachePut(key="#result.id")
            }
    )
    @Override
    @Transactional
    public Prize findByPrizeName(String prizeName) {
        return prizeRepository.findByPrizeName(prizeName);
    }
}
