package com.hmily.community.mapper;

import com.hmily.community.domain.Question;
import com.hmily.community.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    void insertQuestion(Question question);

    List<QuestionDTO> getQuestionList(@Param("search") String search, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize);

    Integer getTotalCount(String search);

    Integer getTotalCountByUserId(Integer id);

    List<QuestionDTO> getQuestionListByUserId(Integer id, Integer offset, Integer pageSize);

    QuestionDTO getQuestionDTOById(Integer id);

    void updateQuestion(Question question);

    void addViewCount(Integer id);

    void addCommentCount(Integer parentId);

    Question getQuestionById(Integer id);

    List<Question> selectReleatedQuestion(Question question);

    List<Question> selectHot();
}
