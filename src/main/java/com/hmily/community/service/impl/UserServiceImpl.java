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

    @Override
    public void createOrUpdateUser(User user) {
        User dbUser = userMapper.getUserByAccountId(user.getAccountId());
        User dbUser2 =  userMapper.getUserByPhoneNumbers(user.getPhoneNumbers());
        if (dbUser == null && dbUser2 == null){
            //插入用户
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }else {
            //更新用户
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setBio(user.getBio());
            userMapper.updateUser(dbUser);
        }
    }

    @Override
    public boolean checkPhoneNumbers(String phoneNumbers) {
      User user  = userMapper.checkPhoneNumbers(phoneNumbers);
       if(user != null){
           return true;
       }
        return false;
    }

    @Override
    public boolean checkPwd(User user) {
        User dbUser = userMapper.checkPwd(user);
        if(dbUser != null){
            return true;
        }
        return false;
    }

    @Override
    public void updateToken(String token, String phoneNumbers) {
        User user = userMapper.getUserByPhoneNumbers(phoneNumbers);
        user.setToken(token);
        userMapper.updateUser(user);
    }


}
