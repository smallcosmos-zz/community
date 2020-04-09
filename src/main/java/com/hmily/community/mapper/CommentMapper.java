package com.hmily.community.mapper;

import com.hmily.community.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 2020/4/8 下午5:49
 * @Created by zhaoli
 */
@Mapper
public interface CommentMapper {
    void insertComment(Comment comment);

    Comment selectCommentById(Integer id);
}
