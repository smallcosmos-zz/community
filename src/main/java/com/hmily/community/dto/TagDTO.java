package com.hmily.community.dto;

import lombok.Data;

import java.util.List;

/**
 *
 * @Date 2020/4/12 下午7:54
 * @Created by zhaoli
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> categoryTags;
}
