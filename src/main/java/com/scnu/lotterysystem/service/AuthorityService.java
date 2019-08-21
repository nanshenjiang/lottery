package com.scnu.lotterysystem.service;

import com.scnu.lotterysystem.entity.Authority;

import java.util.List;

/* *
* authority的service接口
* */
public interface AuthorityService {
    //根据id查询权限
    Authority getAuthorityById(Integer id);

    //查询所有权限
    List<Authority> findAll();
}
