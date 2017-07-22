package com.killkompany.bookshelf.entity;


import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("code")
    String code;

    @SerializedName("msg")
    String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
