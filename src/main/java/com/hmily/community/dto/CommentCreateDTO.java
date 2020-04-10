package com.hmily.community.dto;

import lombok.Data;

/**
 * @Date 2020/4/8 下午5:46
 * @Created by zhaoli
 */
@Data
public class CommentCreateDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
