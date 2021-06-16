package com.yao.handler;

import com.yao.exception.InvalidRequestException;
import com.yao.exception.NotFoundException;
import com.yao.resource.ErrorResource;
import com.yao.resource.FieldResource;
import com.yao.resource.InvalidErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack Yao on 2021/6/15 10:07 上午
 */
/*全局異常處理*/
@RestControllerAdvice/*攔截所有RestController*/
public class ApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /*返回404用ResponseEntity*/
    /*處理NotFound*/
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody/*返回json格式*/
    public ResponseEntity<?> handleNoFound(RuntimeException e){
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        ResponseEntity result = new ResponseEntity<Object>(errorResource, HttpStatus.NOT_FOUND);
        logger.warn("  Return-----------：{}",result);
        return result;
    }
    /*處理參數驗證失敗*/
    @ExceptionHandler(InvalidRequestException.class)/*攔截這個類型*/
    @ResponseBody/*返回json格式*/
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException e){
        /*拿errors，獲取bindingResult*/
        Errors errors = e.getErrors();
        /*從errors裡面獲取每個字串的錯誤，這邊獲取的是list，這邊獲取所有驗證無通過的字段*/
        List<FieldError> fieldErrors = errors.getFieldErrors();
        /*訊息放在這*/
        List<FieldResource> fieldResources = new ArrayList<>();
        /*獲取之後循環*/
        for (FieldError fieldError : fieldErrors){
            /*從fieldError裡面獲取四個錯誤資訊*/
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());
            fieldResources.add(fieldResource);
        }
        /*拿到message，最後再把List<FieldResource>反饋出來*/
        InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(),fieldResources);
        ResponseEntity result = new ResponseEntity<Object>(ier, HttpStatus.BAD_REQUEST);
        logger.warn("  Return-----------：{}",result);
        return new ResponseEntity<Object>(ier,HttpStatus.BAD_REQUEST);/*返回請求驗證沒通過的類型BAD_REQUEST*/
    }
    /*全局異常處理*/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception e){
        logger.error("Error --- {}", e);
        /*伍佰系列版回狀態就好，服務器異常*/
        return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
