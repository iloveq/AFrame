package com.woaiqw.aframe.utils;


public class ApiException extends RuntimeException {

    private String code;

    public String getMsg() {
        return msg;
    }

    private String msg;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;

    }

    public String getCode() {
        return code;
    }
}