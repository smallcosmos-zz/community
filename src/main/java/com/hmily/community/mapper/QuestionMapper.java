package com.hmily.community.mapper;

import com.hmily.community.domain.Question;
import com.hmily.community.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    void insertQuestion(Question question);

    List<QuestionDTO> getQuestionList(Integer offset,Integer pageSize);

    Integer getTotalCount();

    Integer getTotalCountByUserId(Integer id);

    List<QuestionDTO> getQuestionListByUserId(Integer id, Integer offset, Integer pageSize);

    QuestionDTO getQuestionDTOById(Integer id);

    void updateQuestion(Question question);

    void addViewCount(Integer id);

    void addCommentCount(Integer parentId);
}
