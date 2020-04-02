package com.hmily.community.service.impl;

import com.hmily.community.domain.User;
import com.hmily.community.mapper.UserMapper;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.getUserByToken(token);
    }
}
