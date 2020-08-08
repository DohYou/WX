package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

public class DelQAdminModel extends Base {

    /**
     * success : true
     * code : 200
     * msg : 资源修改成功
     * data : null
     */

    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
