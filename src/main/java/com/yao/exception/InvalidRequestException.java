package com.yao.exception;

import org.springframework.validation.Errors;

/**
 * Created by Jack Yao on 2021/6/14 4:25 下午
 */
public class InvalidRequestException extends RuntimeException{
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
