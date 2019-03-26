package com.dy.mmall.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author nic
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,String msg){
        this(status);
        this.msg = msg;
    }

    private ServerResponse(int status,T data){
        this(status);
        this.data = data;
    }

    private ServerResponse(int status,String msg,T data){
        this(status,msg);
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static ServerResponse createBySuccess(){
        return new ServerResponse(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse createBySuccess(T date){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),date);
    }

    public static <T> ServerResponse createBySuccess(String msg,T date){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),msg,date);
    }

    public static ServerResponse createBySuccessMsg(String msg){
        return new ServerResponse(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static ServerResponse createByError(){
        return new ServerResponse(ResponseCode.ERROR.getCode());
    }

    public static ServerResponse createByError(String msg){
        return new ServerResponse(ResponseCode.ERROR.getCode(),msg);
    }

    public static ServerResponse createByError(int status,String msg){
        return new ServerResponse(status,msg);
    }
}
