package com.jmev.VehicleConnector.entity;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Jun
 * @date 2018-11-16 12:36
 */
@Getter
@ToString
public class Response<T> implements Serializable {
    //@formatter:off

    public static final int SUCCESS = 200;

    public static final int FAILURE = 400;

    public static final int ERROR = 500;

    /** 响应编码 */
    int code;

    /** 响应数据 */
    T data;

    /** 响应数据 */
    String msg;

    private Response(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Response<T> success(T data){
        return new Response<>(SUCCESS,data,null);
    }

    public static <T> Response<T> successWithMsg(T data,String msg){
        return new Response<>(SUCCESS,data,msg);
    }

    public static <T> Response<T> failure(String msg){
        return new Response<>(FAILURE,null,msg);
    }

    public static <T> Response<T> error(String msg){
        return new Response<>(ERROR,null,msg);
    }
}
