package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.mapper.UserMapper;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userService.getUserByToken(token);
                request.getSession().setAttribute("user",user);
                break;
            }
        }
        return "index";
    }
}
