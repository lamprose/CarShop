package com.carshop.server.service.impl;

import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.User;
import com.carshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectOneById(String id){
        return userMapper.selectOneById(id);
    }

    @Override
    public User selectOneByToken(String token){
        return userMapper.selectOneByToken(token);
    }

    @Override
    public List<User> selectAllUser(){
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectAllNameSimilar(String name){
        return userMapper.selectAllNameSimilar(name);
    }

    @Override
    public void updateStatusById(String id, String status){
        userMapper.updateStatusById(id, status);
    }

    @Override
    public void updateAvatarById(String avatarPath, String id){
        userMapper.updateAvatarById(avatarPath, id);
    }

    @Override
    public User insertOneUser(User u) {
        userMapper.insertOne(u);
        return u;
    }

}
