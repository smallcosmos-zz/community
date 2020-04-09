package com.hmily.community.controller;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.User;
import com.hmily.community.dto.CommnetDTO;
import com.hmily.community.dto.ResultDTO;
import com.hmily.community.exception.CustomizeErrorCode;
import com.hmily.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/4/8 下午5:45
 * @Created by zhaoli
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/comment")
    @ResponseBody
    public Object insertComment(@RequestBody CommnetDTO commnetDTO,
                                HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commnetDTO.getParentId());
        comment.setContent(commnetDTO.getContent());
        comment.setType(commnetDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        commentService.insertComment(comment);
        return ResultDTO.okOf();
    }
}
