/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.quick.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author gaoruiqiang
 * @Description
 * @create:2022/2/17
 */
@Controller
public class RpcController {

    @RequestMapping("/test/grpc")
    @ResponseBody
    public Object getMerchantList3(HttpServletRequest request) throws InterruptedException {
        HelloWorld_Client client = new HelloWorld_Client("127.0.0.1", 50051);
        try {
            String user = "world";
            client.greet(user);
        } finally {
            client.shutdown();
        }
        return "/test/emop/";
    }
}
