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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize) {
        PageBean<QuestionDTO> pageBean = questionService.getQuestionDTOList(page,pageSize);
        model.addAttribute("pageBean",pageBean);
        return "index";
    }
}
