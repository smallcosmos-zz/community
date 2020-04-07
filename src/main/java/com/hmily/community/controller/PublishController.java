package com.hmily.community.controller;

import com.hmily.community.domain.Question;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**转发到发布页面*/
    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }
    /**编辑*/
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        QuestionDTO question = questionService.getQuestionDTOById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    /**将问题插入数据库*/
    @PostMapping("/publish")
    public String publish(Question question, Model model,HttpServletRequest request){
        //用于错误之后进行回显
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        try {
            questionService.createOrUpdateQuestion(question,request);
           // questionService.insertQuestion(question,request);
            //插入成功，重定向到首页
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
        }

        return "publish";
    }

}
