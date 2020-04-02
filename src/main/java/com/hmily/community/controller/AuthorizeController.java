package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.dto.AccessTokenDTO;
import com.hmily.community.dto.GithubUser;
import com.hmily.community.privoder.GitHubProvider;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
                           HttpServletRequest request
                            ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
        String access_token = gitHubPrivoder.getAccessProvider(accessTokenDTO);
        GithubUser githubUser = gitHubPrivoder.getUser(access_token);
        if(githubUser != null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userService.insertUser(user);
            //登录成功，将user放入session
            request.getSession().setAttribute("githubUser",githubUser);
            return "redirect:/";
        }else {
            //登录失败，返回首页
            return "redirect:/";
        }
    }
}
