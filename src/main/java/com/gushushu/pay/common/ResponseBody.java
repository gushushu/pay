package com.gushushu.pay.common;

public class ResponseBody<T> {
    public static final int SUCCESS_STATUS = 1;
    public static final int ERROR_STATUS = -1;
    private int status;
    private String message;
    private T data;

    public ResponseBody() {
    }

    public ResponseBody(String message) {
        this.message = message;
        this.status = -1;
    }

    public ResponseBody(T data) {
        this.data = data;
        this.status = 1;
    }

    public ResponseBody error(String msg) {
        this.message = msg;
        this.status = -1;
        return this;
    }

    public ResponseBody error(String msg, Object... obj) {
        this.message = String.format(msg, obj);
        this.status = -1;
        return this;
    }

    public ResponseBody error() {
        this.status = -1;
        return this;
    }

    public ResponseBody success() {
        this.status = 1;
        return this;
    }

    public ResponseBody success(T data) {
        this.data = data;
        this.status = 1;
        return this;
    }



    public boolean isSuccess() {
        return this.status == 1;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
