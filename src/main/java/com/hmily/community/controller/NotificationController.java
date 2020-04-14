package com.hmily.community.controller;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.Notification;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.enums.NotificationStatusEnum;
import com.hmily.community.enums.NotificationTypeEnum;
import com.hmily.community.exception.CustomizeErrorCode;
import com.hmily.community.exception.CustomizeException;
import com.hmily.community.service.CommentService;
import com.hmily.community.service.NotificationService;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Date 2020/4/14 上午8:29
 * @Created by zhaoli
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/notification/{id}")
    public String isRead(@PathVariable("id") Long id){
        Notification notification = notificationService.queryById(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.Notify_IS_NULL);
        }
        notification.setStatus(NotificationStatusEnum.NOTIFICATION_READ.getStatus());
        notificationService.update(notification);
        if(notification.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()){
            QuestionDTO questionDTOById = questionService.getQuestionDTOById(notification.getOuterId());
            return "redirect:/question/"+questionDTOById.getId();
        }else{
            Comment comment = commentService.getCommentById(notification.getOuterId());
            QuestionDTO questionDTOById = questionService.getQuestionDTOById(comment.getParentId());
            return "redirect:/question/"+questionDTOById.getId();
        }
    }

}
