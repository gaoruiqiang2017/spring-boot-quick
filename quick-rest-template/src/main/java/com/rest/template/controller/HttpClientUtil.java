package com.rest.template.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.methods.OptionsMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Iterator;

public class HttpClientUtil implements Serializable {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String sendHttp(HttpMethod method, String url, JSONObject requestBody) {
        return sendHttp(method, url, requestBody, null);
    }

    public static String sendHttp(HttpMethod method, String url, JSONObject requestBody,
                                  JSONObject headers) {
        try {
            log.info("即将要发送的url为:" + url);
            HttpClient client = new HttpClient();
            HttpMethodBase httpMethod;
            switch (method) {
                case GET:
                    httpMethod = new GetMethod(url);
                    break;
                case HEAD:
                    httpMethod = new HeadMethod(url);
                    break;
                case PUT:
                    httpMethod = new PutMethod(url);
                    httpMethod.setRequestHeader("Content-type", "application/json");
                    if (requestBody != null) {
                        RequestEntity requestEntity =
                            new StringRequestEntity(requestBody.toString(), "application/json",
                                "UTF-8");
                        PutMethod putMethod = (PutMethod) httpMethod;
                        putMethod.setRequestEntity(requestEntity);
                    }
                    break;
                case DELETE:
                    httpMethod = new DeleteMethod(url);
                    break;
                case POST:
                    httpMethod = new PostMethod(url);
                    httpMethod.setRequestHeader("Content-type", "application/json");
                    if (requestBody != null) {
                        RequestEntity requestEntity =
                            new StringRequestEntity(requestBody.toString(), "application/json",
                                "UTF-8");
                        PostMethod postMethod = (PostMethod) httpMethod;
                        postMethod.setRequestEntity(requestEntity);
                    }
                    break;
                case OPTIONS:
                    httpMethod = new OptionsMethod(url);
                    break;
                case POST_FORM:
                    httpMethod = new PostMethod(url);
                    httpMethod.setRequestHeader("Content-type", "application/x-www-form" +
                        "-urlencoded");
                    if (requestBody != null) {
                        Iterator iterator = requestBody.keySet().iterator();
                        String key;
                        while (iterator.hasNext()) {
                            key = (String) iterator.next();
                            ((PostMethod) httpMethod).addParameter(key, requestBody.getString(key));
                        }
                    }
                    break;
                default:
                    httpMethod = new GetMethod(url);
            }
            if (null != headers) {
                Iterator iterator = headers.keySet().iterator();
                String key;
                while (iterator.hasNext()) {
                    key = (String) iterator.next();
                    httpMethod.setRequestHeader(key, headers.getString(key));
                }
            }
            int executeMethod = client.executeMethod(httpMethod);
            if (executeMethod != 200) {
                log.error("请求API失败，状态码为:" + executeMethod);
            }
            log.info("结果为:" + httpMethod.getResponseBodyAsString());
            return httpMethod.getResponseBodyAsString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (e.getClass().getName().equals("java.net.ConnectException")) {
                return "ConnectException";
            }
        }
        return "";
    }
}
