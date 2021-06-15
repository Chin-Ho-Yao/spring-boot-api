package com.yao.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jack Yao on 2021/6/15 3:40 下午
 */
@RestController
public class LogTestApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*獲取log日誌*/
    @GetMapping("/log")
    public String log(){
        String name = "zhang";
        String email = "hell@163.com";
        logger.info("info --- log");
        logger.warn("warn --- log");
        logger.error("error --- log");
        logger.debug("debug --- log");
        logger.info("name:{},email:{}", name, email);
        return "logtest";
    }
}
