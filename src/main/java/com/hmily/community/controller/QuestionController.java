package com.hmily.community.controller;

import com.hmily.community.dto.CommentDTO;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.enums.CommentTypeEnum;
import com.hmily.community.service.CommentService;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zhaoli
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getQuestionDTOById(id);
        List<CommentDTO> comments =  commentService.getCommentDTOByTargetId(id, CommentTypeEnum.QUESTION);
        for (CommentDTO comment : comments) {
            comment.setCommentCount(commentService.getCommentCountById(comment.getId()));
        }

        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
