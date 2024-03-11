//package com.dubbo.consumer.service;
//
//import com.dubbo.api.HelloService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HelloServiceImpl implements HelloService {
//
//    @DubboReference
//    public HelloService helloService;
//
//    @Override
//    public String sayHello(String name) {
//        return helloService.sayHello(name);
//    }
//}
