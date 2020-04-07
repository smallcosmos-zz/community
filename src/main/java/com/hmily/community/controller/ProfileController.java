package com.hmily.community.controller;

import com.hmily.community.domain.User;
import com.hmily.community.dto.PageBean;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.service.QuestionService;
import com.hmily.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                          Model model){
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }
        User user = (User) request.getSession().getAttribute("user");

        if(user == null){
            return "redirect:/";
        }
        PageBean<QuestionDTO> pageBean = questionService.getQuestionDTOListByUserId(user.getId(),page,pageSize);
        model.addAttribute("pageBean",pageBean);
        return "profile";
    }
}
