package com.hmily.community.service;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.User;
import com.hmily.community.dto.CommnetDTO;

/**
 * @Date 2020/4/8 下午5:54
 * @Created by zhaoli
 */
public interface CommentService {
    void insertComment(Comment comment);
}
