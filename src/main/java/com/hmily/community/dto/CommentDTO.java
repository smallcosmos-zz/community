package com.hmily.community.dto;

import com.hmily.community.domain.User;
import lombok.Data;

/**
 * @Date 2020/4/10 下午2:04
 * @Created by zhaoli
 */
@Data
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
