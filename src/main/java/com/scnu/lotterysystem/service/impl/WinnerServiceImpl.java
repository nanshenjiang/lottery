package com.scnu.lotterysystem.service.impl;

import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.entity.Winner;
import com.scnu.lotterysystem.repository.WinnerRepository;
import com.scnu.lotterysystem.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@CacheConfig(cacheNames = "winner",cacheManager = "winnerCacheManager")
@Service
public class WinnerServiceImpl implements WinnerService {
    @Autowired
    private WinnerRepository winnerRepository;

    @Transactional
    @Override
    public List<Winner> findAll() {
        return winnerRepository.findAll();
    }

    @Override
    @Transactional
    public List<Winner> findByUser(User user) {
        return winnerRepository.findByUser(user);
    }

    @CachePut(key="#result.id")
    @Transactional
    @Override
    public Winner saveWinner(Winner winner) {
        try {
            winnerRepository.save(winner);
        }catch (Exception e){
            throw new RuntimeException("Add Winner Error: "+e.getMessage());
        }
        return  winner;
    }

    @CacheEvict(key="#id")
    @Transactional
    @Override
    public void removeWinner(Integer id) {
        winnerRepository.delete(id);
    }
}
