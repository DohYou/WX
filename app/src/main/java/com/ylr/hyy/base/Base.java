package com.ylr.hyy.base;

import java.io.Serializable;

public class Base implements Serializable {
    private int code;
    private String msg;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
