package com.util;

public class Response<T> {
    Error error;
    T data;

    public Response() {
    }

    public Response(Error error, T data) {
        this.error = error;
        this.data = data;
    }

    public Response(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
