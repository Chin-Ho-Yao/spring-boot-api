package com.yao.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Jack Yao on 2021/6/16 2:42 下午
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.yao.api.LogTestApi.log(..))")
    public void log(){
    }
    /*切入點之前*/

    @Before("log()")
    public void doBefore(){
        logger.info("---------doBefore  1-----------");
    }

    @After("log()")
    public void doAfter(){
        logger.info("---------doAfter  2-----------");
    }
    /*切入點log()返回後才執行這個*/
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("---------doAfterReturning  2-----------：內容{}",result);
    }

}
