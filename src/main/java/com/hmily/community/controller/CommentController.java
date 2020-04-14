package com.hmily.community.controller;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.User;
import com.hmily.community.dto.CommentCreateDTO;
import com.hmily.community.dto.CommentDTO;
import com.hmily.community.dto.ResultDTO;
import com.hmily.community.enums.CommentTypeEnum;
import com.hmily.community.exception.CustomizeErrorCode;
import com.hmily.community.exception.CustomizeException;
import com.hmily.community.service.CommentService;
import com.hmily.community.service.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Date 2020/4/8 下午5:45
 * @Created by zhaoli
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotificationService notificationService;
    @PostMapping("/comment")
    @ResponseBody
    public Object insertComment(@RequestBody CommentCreateDTO commentCreateDTO,
                                HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || !StringUtils.isNotBlank(commentCreateDTO.getContent())){
            throw  new CustomizeException(CustomizeErrorCode.CONTENT_IS_NULL);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.insertComment(comment);
        Integer unreadCount = notificationService.queryUnreadCount(user.getId());
        request.getSession().setAttribute("unreadCount",unreadCount);
        return ResultDTO.okOf();
    }

    @GetMapping("/comment/{id}")
    @ResponseBody
    public List<CommentDTO> getComment(@PathVariable("id") Integer id){
        List<CommentDTO> commentDTOS =  commentService.getCommentDTOByTargetId(id, CommentTypeEnum.COMMENT);
        return commentDTOS;
    }
}

