package com.yao.resource;

/**
 * Created by Jack Yao on 2021/6/15 10:05 上午
 */
public class InvalidErrorResource {
    private String message;
    private Object errors;

    public InvalidErrorResource(String message, Object errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public Object getErrors() {
        return errors;
    }
}
