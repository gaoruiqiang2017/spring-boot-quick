package com.quick.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.quick.redis.service.CompanyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */
@RestController
public class ApiController {

    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/isCompany/{company}", method = RequestMethod.GET)
    public Integer isCompamy(@PathVariable("company") String company) {
        return companyService.isCompany(company);
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer isCompamy() {
        return companyService.add();
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Integer del() {
        return companyService.del();
    }


    @RequestMapping(value = "/string/set", method = RequestMethod.GET)
    public Integer set() {
        System.out.println("redis /string/set");
        String key = "string2";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("raw_value", "娃哈哈(北京)");
        jsonObject.put("clean_value", "娃哈哈(北京)有限公司");
        jsonObject.put("isLabel", 1);
        companyService.set(key,jsonObject.toString());

        return 1;
    }

    @RequestMapping(value = "/string/get", method = RequestMethod.GET)
    public String get() {
        System.out.println("redis /string/get");
        return companyService.get("string2");
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushDB();
        System.out.println("========添加一个list========" );
        jedis.lpush(" collections", "ArrayList", "Vector", "Stack","HashMap","weakHashMap","LinkedHashMap","");
        jedis.lpush("collections","HashSet");
        jedis.lpush("colllections","TreeSet");
        jedis.lpush("collections","TreeMap");
        //-1代表倒数第一个元素，0代表第一个元素
        System.out.println("collections的内容: "+jedis.lrange("collections",0,-1));
        System.out . println("collections区间0-3的元素:"+jedis.lrange("collections" ,0,3));
        System.out.println( "=============================" );
        //删除列表指定的值，第二个参数为删除的个数（有重复时)，后add进去的值先被删，类似于出栈
        System.out .println("删除指定元素个数:"+jedis.lrem("collections",2,"HashMap"));
        System.out.println( " collections的内容: "+jedis.lrange("collections",0,-1));
        //截断元素
        System.out.println("删除下表0-3区间之外的元素:"+jedis.ltrim("collections",0,3));
        System.out.println( " collections的内容: "+jedis.lrange("collections",0,-1));
        System.out .println("collections列表出栈(左端): "+jedis.lpop("collections"));
        System.out.println( "collections的内容: "+jedis.lrange("collections",0,-1));
        System.out .println(" collections添加元素，从列表右端，与lpush相对应:"+jedis.rpush("collections","EnumMap"));
        System.out. println(" collections的内容: "+jedis.lrange("collections",0,-1));
        System.out.println( " collections列表出栈（右端):"+jedis.rpop("collections"));
        System.out.println( " collections的内容: "+jedis.lrange("collections",0,-1));
        System.out.println("修改collections指定下标1的内容: "+jedis.lset("collections",1,"LinkedHashMap"));
        System.out.println(" collections的内容: "+jedis.lrange("collections",0,-1));
        System.out.println("==============================" );
        System.out.println("collections的长度:"+jedis.llen("collections"));
        System.out.println("获取collections下标为2的元素: "+jedis.lindex("collections",2));
        System.out.println( "=============================" );
        jedis.lpush("sortedList","3","6","2","0","7","4");
        System.out .println( " sortedList排序前:"+jedis.lrange("sortedList",0,-1));
        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后："+jedis.lrange("sortedList",0,-1));

    }

}
