package com.scnu.lotterysystem.service.impl;

import com.scnu.lotterysystem.entity.User;
import com.scnu.lotterysystem.repository.UserRepository;
import com.scnu.lotterysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }


    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        try{
           userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Add User Error: "+e.getMessage());
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
         userRepository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }//用户不存在要抛出异常
        return user;
    }
}
