package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.privoder.MD5Utils;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Date 2020/5/2 下午3:53
 * @Created by zhaoli
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @GetMapping("register")
    public String toRegister(){
        return "register";
    }

    @GetMapping("/register/{phoneNumbers}")
    @ResponseBody
    public boolean checkPhoneNumbers(@PathVariable("phoneNumbers") String phoneNumbers){
        boolean flag = userService.checkPhoneNumbers(phoneNumbers);
        return flag;
    }

    @PostMapping("register")
    public String register(User user ,@RequestParam(name = "confirmCode") String confirmCode){
        String code = redisTemplate.opsForValue().get(user.getPhoneNumbers());
        if(code.equals(confirmCode)){
            user.setAvatarUrl("/images/default-avatar.png");
            user.setToken(UUID.randomUUID().toString());
            user.setPassword(MD5Utils.getMD5(user.getPassword()));
            userService.createOrUpdateUser(user);
        }
        return "login";
    }
}
