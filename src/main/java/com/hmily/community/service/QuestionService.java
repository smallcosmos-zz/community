package com.hmily.community.service;

import com.hmily.community.domain.Question;
import com.hmily.community.dto.PageBean;
import com.hmily.community.dto.QuestionDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QuestionService {

    PageBean<QuestionDTO> getQuestionDTOList(String search, Integer page, Integer paeSize);

    PageBean<QuestionDTO> getQuestionDTOListByUserId(Integer id, Integer page, Integer pageSize);

    QuestionDTO getQuestionDTOById(Integer id);

    void createOrUpdateQuestion(Question question, HttpServletRequest request);

    List<Question> selectReleatedQuestion(QuestionDTO question);

    List<Question> selectHot();
}
