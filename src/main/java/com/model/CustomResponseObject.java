package com.model;

public class CustomResponseObject<T> {


    T data;
    ExceptionPojo error;
    int statusCode;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ExceptionPojo getError() {
        return error;
    }

    public void setError(ExceptionPojo error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
