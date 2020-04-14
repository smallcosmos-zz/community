package com.hmily.community.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 通知表(Notification)实体类
 *
 * @author makejava
 * @since 2020-04-13 13:45:56
 */
@Data
public class Notification implements Serializable {
    private static final long serialVersionUID = 725314867437532325L;

    private Long id;
    /**
     * 通知者
     */
    private Integer notifier;
    /**
     * 被通知者
     */
    private Integer receiver;
    /**
     * 标识回复的问题还是评论
     */
    private Integer type;
    /**
     * '0'代表未读
     * ‘1’代表已读
     */
    private Integer status;

    private Long gmtCreate;

    private Integer outerId;

}