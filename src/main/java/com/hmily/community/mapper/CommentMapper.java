package com.hmily.community.mapper;

import com.hmily.community.domain.Comment;
import com.hmily.community.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Date 2020/4/8 下午5:49
 * @Created by zhaoli
 */
@Mapper
public interface CommentMapper {
    void insertComment(Comment comment);

    Comment selectCommentById(Integer id);

    List<CommentDTO> getCommentDTOByTargetId(Integer id, Integer type);


    Long getCommentCountById(Integer id);
}
