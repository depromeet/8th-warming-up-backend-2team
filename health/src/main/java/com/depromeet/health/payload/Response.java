package com.depromeet.health.payload;

public class Response<T> {
    private String message;
    private Boolean success;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response() {
        this.message = "good";
        this.success = true;
        this.data = null;
    }

    public Response(String message, Boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public Response(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
