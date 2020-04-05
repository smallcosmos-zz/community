package com.hmily.community.service;

import com.hmily.community.domain.Question;
import com.hmily.community.dto.PageBean;
import com.hmily.community.dto.QuestionDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {
    void insertQuestion(Question question, HttpServletRequest request);

    PageBean<QuestionDTO> getQuestionDTOList(Integer page, Integer paeSize);

    PageBean<QuestionDTO> getQuestionDTOListByUserId(Integer id, Integer page, Integer pageSize);
}
