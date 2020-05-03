package com.hmily.community.service;

import com.hmily.community.domain.User;

public interface UserService {

    void insertUser(User user);

    User getUserByToken(String token);

    void createOrUpdateUser(User user);

    boolean checkPhoneNumbers(String phoneNumbers);

    boolean checkPwd(User user);

    void updateToken(String token, String phoneNumbers);
}
