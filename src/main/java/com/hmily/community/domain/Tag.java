package com.hmily.community.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author makejava
 * @since 2020-04-12 13:37:04
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 471115672055463952L;
    
    private Long id;
    
    private String categoryCode;
    
    private String categoryName;
    
    private String categoryTag;




}