package com.hmily.community.service;

import com.hmily.community.domain.User;
import com.hmily.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    void insertUser(User user);
}
