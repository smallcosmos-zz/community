package com.hmily.community.controller;

import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getQuestionDTOById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
