package com.scnu.lotterysystem.service;

import com.scnu.lotterysystem.entity.User;

import java.util.List;

/* *
* user的service层接口
* */
public interface UserService  {

    List<User> findAll();

    User findById(Integer id);

    User saveOrUpdateUser(User user);

    void deleteById(Integer id);

}
