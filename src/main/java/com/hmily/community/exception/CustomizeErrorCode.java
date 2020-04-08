package com.hmily.community.exception;

/**
 * @author zhaoli
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QEUSTION_NOT_FONUF("你找的问题不在了，要不要换一个试试?");
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
