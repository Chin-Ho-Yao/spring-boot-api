package com.yao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by Jack Yao on 2021/6/16 2:42 下午
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*對應LogTestApi類得log方法*/
//    @Pointcut("execution(* com.yao.api.LogTestApi.log(..))")
    /*對應api包所有類的所有方法*/
    @Pointcut("execution(* com.yao.api.*.*(..))")
    public void log(){
    }
    /*切入點之前*/

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        /*調用方法之前先記錄下來，通常請求都先獲取HttpRequest，要強制轉換成ServletRequestAttributes*/
        ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /*獲得類別名getDeclaringTypeName 獲得方法名getName*/
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName();
        RequestLog requestLog = new RequestLog(
                request.getRequestURI().toString(),
                request.getRemoteAddr(),    /*獲取ip*/
                classMethod,                  /*類與方法只能通過切入點獲取*/
                joinPoint.getArgs()         /*獲取所有請求參數*/
        );
        logger.info("  Request  :  ---------{}",requestLog);
    }

    @After("log()")
    public void doAfter(){
//        logger.info("---------doAfter  2-----------");
    }
    /*切入點log()返回後才執行這個*/
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("  Return-----------：內容{}",result);
    }

    /*定義url ip 類和方法 參數，其中方法可能是不同類型所以不能用String*/
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
        /*要輸出在日誌所以要toStroing方法*/
        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }


}
