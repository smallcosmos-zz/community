package com.hmily.community.domain;

import lombok.Data;

/**
 * @Date 2020/4/8 下午5:40
 * @Created by zhaoli
 */
@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
}
