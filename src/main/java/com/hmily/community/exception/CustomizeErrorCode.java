package com.hmily.community.exception;

/**
 * @author zhaoli
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QEUSTION_NOT_FONUF(2001, "你回复的问题不在了，要不要换一个试试?"),
    TARGET_PARAM_NOT_FONUF(2002, "未选中任何评论或回复"),
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004, "服务已经冒烟了,要不你稍后再试试~"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不在了，要不换一个试试?"),
    ;
    private String message;
    private Integer code;
    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
