package com.hmily.community.controller;

import com.hmily.community.domain.Notification;
import com.hmily.community.domain.User;
import com.hmily.community.dto.NotificationDTO;
import com.hmily.community.dto.PageBean;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.service.NotificationService;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
   @Autowired
   private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "pageSize",defaultValue = "5")Integer pageSize,
                          Model model){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        Integer unreadCount = notificationService.queryUnreadCount(user.getId());
        request.getSession().setAttribute("unreadCount",unreadCount);
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            PageBean<QuestionDTO> pageBean = questionService.getQuestionDTOListByUserId(user.getId(),page,pageSize);
            model.addAttribute("pageBean",pageBean);

        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
            PageBean<NotificationDTO> notifies = notificationService.getNotificationDTOListByUserId(user.getId(),page,pageSize);
            model.addAttribute("pageBean",notifies);
        }

        return "profile";
    }
}
