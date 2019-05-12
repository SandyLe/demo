package com.sl.domain.dto.util;

public class Result<T> {

    public Result() {
    }

    public Result(Object data) {
        this.data = data;
    }

    private String code;

    private String errorMsg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
