package com.killkompany.bookshelf.entity;


import com.google.gson.annotations.SerializedName;

public class ResponseWrapper<T> {

    @SerializedName("result")
    T result;

    @SerializedName("error")
    Error error;

    public boolean hasError(){
        return error != null;
    }

    public T getResult() {
        return result;
    }

    public Error getError() {
        return error;
    }
}
