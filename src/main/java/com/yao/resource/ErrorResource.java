package com.yao.resource;

/**
 * Created by Jack Yao on 2021/6/15 9:59 上午
 */
public class ErrorResource {
    private String message;

    public ErrorResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
