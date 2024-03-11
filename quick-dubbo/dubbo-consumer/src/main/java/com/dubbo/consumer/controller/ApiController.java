package com.dubbo.consumer.controller;

import com.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @DubboReference(version = "1.0.0")
    public HelloService helloService;

    @RequestMapping("/sayHello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }

}
