package com.dubbo.producer.service;

import com.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
