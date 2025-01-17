package com.wx.pn.utils;


/**
 * Created by wei on 2016/12/12.
 */
public class BaseResp<T> {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    private String currentTime;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public BaseResp(){
        //this.currentTime = DateUtils.format(new Date(),DateUtils.defaultDateTimeFormat);
    }

    /*public BaseResp(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.currentTime = DateUtils.format(new Date(),DateUtils.defaultDateTimeFormat);
    }

    public BaseResp(int code, ResultStatus resultStatus,String message) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.currentTime = DateUtils.format(new Date(),DateUtils.defaultDateTimeFormat);
    }

    public BaseResp(ResultStatus resultStatus) {
        this.code = resultStatus.getErrorCode();
        this.message = resultStatus.getErrorMsg();
        this.data = data;
        this.currentTime = DateUtils.format(new Date(),DateUtils.defaultDateTimeFormat);
    }

    public BaseResp(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getErrorCode();
        this.message = resultStatus.getErrorMsg();
        this.data = data;
        this.currentTime = DateUtils.format(new Date(),DateUtils.defaultDateTimeFormat);
    }*/


}

