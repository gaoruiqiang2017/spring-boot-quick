package com.logback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogBackController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/logback")
    @ResponseBody
    public String logback() {

        logger.trace("日志输出:{} trace:{}","我","trace");
        logger.debug("日志输出:{} debug:{}","我","debug");
        logger.info("日志输出:{} info:{}","我","info");
        logger.warn("日志输出:{} warn:{}","我","warn");
        logger.error("日志输出:{} error:{}","我","error");
        for (int i = 0; i < 1; i++) {
            // 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR <
            // FATAL，如果设置为WARN，则低于WARN的信息都不会输出。

        }
        return "Hello world";
    }

}
