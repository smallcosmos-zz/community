package com.hmily.community.dto;

import lombok.Data;

/**
 * @Date 2020/4/14 下午12:15
 * @Created by zhaoli
 */
@Data
public class FileDTO {
    /**
     *      success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     *     message : "提示的信息，上传成功或上传失败及错误信息等。",
     *     url     : "图片地址"        // 上传成功时才返回
     */
    private Integer success;
    private String message;
    private String url;
}
