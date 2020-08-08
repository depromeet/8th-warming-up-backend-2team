package com.depromeet.health.payload;

public class Request<T> {
    private T data;

    public Request(T data) {
        this.data = data;
    }

    Request() {
        this.data = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
