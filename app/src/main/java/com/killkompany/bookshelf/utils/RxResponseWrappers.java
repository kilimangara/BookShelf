package com.killkompany.bookshelf.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.killkompany.bookshelf.entity.ResponseWrapper;
import com.killkompany.bookshelf.entity.User;
import com.killkompany.bookshelf.entity.UserWrapper;
import com.killkompany.bookshelf.exceptions.ResponseErrorException;


public class RxResponseWrappers {

    private static final String USER_WRAPPER = UserWrapper.class.getName();

    public static <T> T  proceedResponseOrException(String response, Class<T> resultClass) throws ResponseErrorException{
//        Gson gson = new Gson();
//        ResponseWrapper<T> responseWrapper = gson.fromJson(response, new TypeToken<ResponseWrapper<T>>(){}.getType());
//        if( responseWrapper.hasError()) throw new ResponseErrorException(responseWrapper.getError());
//        return responseWrapper.getResult();
        Log.d("test",resultClass.getSimpleName());
        switch (resultClass.getSimpleName()){
            case "UserWrapper":
                return (T) proccedUserWrapper(response);
            default:
                return null;
        }
    }

    private static UserWrapper proccedUserWrapper(String response){
        Gson gson = new Gson();
        ResponseWrapper<UserWrapper> responseWrapper = gson.fromJson(response, new TypeToken<ResponseWrapper<UserWrapper>>(){}.getType());
        if( responseWrapper.hasError()) throw new ResponseErrorException(responseWrapper.getError());
        return responseWrapper.getResult();
    }
}
