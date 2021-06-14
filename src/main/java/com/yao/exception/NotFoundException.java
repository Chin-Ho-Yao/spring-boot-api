package com.yao.exception;

/**
 * Created by Jack Yao on 2021/6/14 4:13 下午
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
