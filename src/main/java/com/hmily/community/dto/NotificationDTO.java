package com.hmily.community.dto;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.Question;
import com.hmily.community.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 通知表(Notification)实体类
 *
 * @author makejava
 * @since 2020-04-13 13:45:56
 */
@Data
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 725314867437532325L;

    private Long id;
    private Integer notifier;
    private Integer receiver;
    private Integer type;
    private Integer status;
    private Long gmtCreate;
    private Integer outerId;
    private Question question;
    private Comment comment;
    private User user;

}