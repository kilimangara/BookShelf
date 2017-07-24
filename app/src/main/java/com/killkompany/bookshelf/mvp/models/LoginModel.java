package com.killkompany.bookshelf.mvp.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.killkompany.bookshelf.entity.ResponseWrapper;
import com.killkompany.bookshelf.entity.User;
import com.killkompany.bookshelf.exceptions.ResponseErrorException;
import com.killkompany.bookshelf.mvp.viewholders.LoginViewHolder;
import com.killkompany.bookshelf.mvp.views.LoginView;
import com.killkompany.bookshelf.netcore.RequestSender;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LoginModel extends BaseModel<LoginView, LoginViewHolder> {

    public LoginModel(LoginView view) {
        super(view);
        Observable.fromCallable(RequestSender.checkUser()).subscribeOn(Schedulers.newThread())
                .map(new Function<String, User>() {
                    @Override
                    public User apply(@NonNull String s) throws Exception {
                        Gson gson = new GsonBuilder().create();
                        ResponseWrapper<User> response = gson.fromJson(s, new TypeToken<ResponseWrapper<User>>(){}.getType());
                        if(response.hasError()) throw new ResponseErrorException(response.getError());
                        return response.getResult();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        if(user != null){

                        }
                    }
                }).subscribe();
    }
}
