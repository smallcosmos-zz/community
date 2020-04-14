package com.hmily.community.enums;

/**
 * @Date 2020/4/13 下午3:32
 * @Created by zhaoli
 */
public enum NotificationStatusEnum {
    NOTIFICATION_UNREAD(0,"消息未读"),
    NOTIFICATION_READ(1,"消息已读"),
    ;
    private Integer status;
    private String msg;

    NotificationStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
