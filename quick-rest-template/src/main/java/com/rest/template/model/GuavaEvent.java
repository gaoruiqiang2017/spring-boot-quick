package com.rest.template.model;

import com.google.common.eventbus.Subscribe;

/**
 * @author gaoruiqiang
 * @version 1.0
 * @description: TODO
 * @date 2023/5/9 15:39
 */
public class GuavaEvent {


    @Subscribe
    public void subscribe(String str){
        //业务逻辑
        System.out.println("执行subscribe 方法,传入的参数是:" + str);
    }

}
