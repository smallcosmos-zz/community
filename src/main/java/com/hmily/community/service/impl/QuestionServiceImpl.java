package com.hmily.community.service.impl;

import com.hmily.community.domain.Question;
import com.hmily.community.domain.User;
import com.hmily.community.dto.PageBean;
import com.hmily.community.dto.QuestionDTO;
import com.hmily.community.exception.CustomizeErrorCode;
import com.hmily.community.exception.CustomizeException;
import com.hmily.community.mapper.QuestionMapper;
import com.hmily.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public PageBean<QuestionDTO> getQuestionDTOList(Integer page, Integer pageSize) {
        Integer totalCount = questionMapper.getTotalCount();
        PageBean<QuestionDTO> pageBean = new PageBean<>(page, pageSize, totalCount);

        List<QuestionDTO> list = questionMapper.getQuestionList(pageBean.getOffset(), pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public PageBean<QuestionDTO> getQuestionDTOListByUserId(Integer id, Integer page, Integer pageSize) {
        Integer totalCount = questionMapper.getTotalCountByUserId(id);
        PageBean<QuestionDTO> pageBean = new PageBean<>(page, pageSize, totalCount);

        List<QuestionDTO> list = questionMapper.getQuestionListByUserId(id, pageBean.getOffset(), pageBean.getPageSize());
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public QuestionDTO getQuestionDTOById(Integer id) {
        QuestionDTO questionDTOById = questionMapper.getQuestionDTOById(id);
        if(questionDTOById == null){
            throw new CustomizeException(CustomizeErrorCode.QEUSTION_NOT_FONUF);
        }
        return questionDTOById;
    }

    @Override
    public void createOrUpdateQuestion(Question question, HttpServletRequest request) {

        if (question.getTitle() == null || "".equals(question.getTitle())) {
            throw new RuntimeException("问题标题不能为空");
        }
        if (question.getDescription() == null || "".equals(question.getDescription())) {
            throw new RuntimeException("问题补充不能为空");
        }
        if (question.getTag() == null || "".equals(question.getTag())) {
            throw new RuntimeException("问题标签不能为空");
        }
        if (question.getId() != null) {
            try {
                //更新
                question.setGmtModified(System.currentTimeMillis());
                questionMapper.updateQuestion(question);
            } catch (Exception e) {
                throw new CustomizeException(CustomizeErrorCode.QEUSTION_NOT_FONUF);
            }

        } else {
            //插入
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                throw new RuntimeException("请登录后在进行发表问题");
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
    }
}
