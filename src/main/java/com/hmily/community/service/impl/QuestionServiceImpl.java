package com.hmily.community.service.impl;

import com.hmily.community.domain.Question;
import com.hmily.community.domain.User;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.mapper.QuestionMapper;
import com.hmily.community.service.QuestionService;
import com.hmily.community.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserService userService;
    @Override
    public void insertQuestion(Question question, HttpServletRequest request) {

        if(question.getTitle() == null || "".equals(question.getTitle())){
            throw new RuntimeException("问题标题不能为空");
        }
        if(question.getDescription() == null || "".equals(question.getDescription())){
            throw new RuntimeException("问题补充不能为空");
        }
        if(question.getTag() == null || "".equals(question.getTag())){
            throw new RuntimeException("问题标签不能为空");
        }
        User user = new User();
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            throw new RuntimeException("请登录后在进行发表问题");
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userService.getUserByToken(token);
                    if(user == null){
                        throw new RuntimeException("请登录后在进行发表问题");
                    }else {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        try {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCreator(user.getId());
            questionMapper.insertQuestion(question);
        } catch (Exception e) {
            throw new RuntimeException("问题发表失败");
        }
    }

    @Override
    public List<QuestionDTO> getQuestionDTOList() {
        List<QuestionDTO> list  = questionMapper.getQuestionList();

        return list;
    }
}
