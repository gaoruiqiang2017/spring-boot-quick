package com.example.quickhttpclient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoruiqiang
 * @version 1.0
 * @description: TODO
 * @date 2022/12/28 17:14
 */
@RestController
@RequestMapping("/httpclient")
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public void test() throws Exception {
        String url = "http://127.0.0.1:8080/testGet";
        //HttpUtil.HttpResult httpResult = HttpUtil.httpGet(url);
        HttpClientUtil.HttpClientResult httpClientResult = HttpClientUtil.doGet(url);
        System.out.println("response=="+httpClientResult.getContent());


    }

}
