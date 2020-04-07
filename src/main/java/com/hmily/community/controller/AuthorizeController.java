package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.dto.AccessTokenDTO;
import com.hmily.community.dto.GithubUser;
import com.hmily.community.privoder.GitHubProvider;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    GitHubProvider gitHubPrivoder;
    @Autowired
    UserService userService;
    @Value("${github.client.id}")
    String clientId;
    @Value("${github.client.secret}")
    String clientSecret;
    @Value("${github.redirect.uri}")
    String redirectUrl;
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response
                            ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String access_token = gitHubPrivoder.getAccessProvider(accessTokenDTO);
        GithubUser githubUser = gitHubPrivoder.getUser(access_token);
        if(githubUser != null && githubUser.getId() != null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setBio(githubUser.getBio());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdateUser(user);
            //登录成功，将token放入cookie
            Cookie cookie = new Cookie("token", token);
            //设置cookie存在时间
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            //登录失败，返回首页
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public  String logOut(HttpServletRequest request ,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
