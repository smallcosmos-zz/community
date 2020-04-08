package com.hmily.community.exception;

/**
 * @author zhaoli
 */
public class CustomizeException extends  RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage()  ;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
