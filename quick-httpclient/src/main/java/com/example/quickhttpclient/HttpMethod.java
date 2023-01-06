package com.example.quickhttpclient;

public enum HttpMethod {
    GET("GET"),
    HEAD("HEAD"),
    PUT("PUT"),
    DELETE("DELETE"),
    POST("POST"),
    OPTIONS("OPTIONS"),
    POST_FORM("POST_FORM");

    private String value;
    HttpMethod(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
