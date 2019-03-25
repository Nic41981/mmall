package com.dy.mmall.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author nic
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private int code;
    private String msg;
}
