package com.killkompany.bookshelf.exceptions;


import com.killkompany.bookshelf.entity.Error;

public class ResponseErrorException extends RuntimeException {

    private Error error;

    public ResponseErrorException(Error error){
        this.error = error;
    }

    public Error getError(){
        return error;
    }
}
