package com.scnu.lotterysystem.service.impl;

import com.scnu.lotterysystem.entity.Authority;
import com.scnu.lotterysystem.repository.AuthorityRepository;
import com.scnu.lotterysystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    public Authority getAuthorityById(Integer id) {
        return authorityRepository.findOne(id);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
