package com.hmily.community.enums;

/**
 * @Date 2020/4/13 下午3:32
 * @Created by zhaoli
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"问题回复"),
    REPLY_COMMNET(2,"评论回复"),
    ;
    private Integer type;
    private String notification;

    NotificationTypeEnum(Integer type, String notification) {
        this.type = type;
        this.notification = notification;
    }

    public Integer getType() {
        return type;
    }

    public String getNotification() {
        return notification;
    }
}
