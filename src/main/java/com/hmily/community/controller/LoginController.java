package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.privoder.MD5Utils;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Date 2020/5/2 下午12:00
 * @Created by zhaoli
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("login")
    public String login(String phoneNumbers, String password, Model model, HttpServletResponse response){
        //判断当前手机号是否注册
        boolean b = userService.checkPhoneNumbers(phoneNumbers);
        if(!b){
            //未注册
            model.addAttribute("msg","该手机号码未注册，请注册后进行登录!");
            return "login";
        }
        //判断密码是否正确
        User user = new User();
        user.setPhoneNumbers(phoneNumbers);
        user.setPassword(MD5Utils.getMD5(password));
        boolean isRight= userService.checkPwd(user);
        if (isRight){
            String token = UUID.randomUUID().toString();
            userService.updateToken(token,phoneNumbers);
            //登录成功，将token放入cookie
            Cookie cookie = new Cookie("token", token);
            //设置cookie存在时间
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            model.addAttribute("msg","密码不正确");
            return "login";
        }

    }

}
