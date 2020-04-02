package com.hmily.community.mapper;

import com.hmily.community.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User getUserByToken(String token);
}
