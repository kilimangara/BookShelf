package com.killkompany.bookshelf.mvp.models;

import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.killkompany.bookshelf.R;
import com.killkompany.bookshelf.entity.ResponseWrapper;
import com.killkompany.bookshelf.entity.User;
import com.killkompany.bookshelf.entity.UserWrapper;
import com.killkompany.bookshelf.exceptions.ResponseErrorException;
import com.killkompany.bookshelf.mvp.viewholders.LoginViewHolder;
import com.killkompany.bookshelf.mvp.views.LoginView;
import com.killkompany.bookshelf.netcore.RequestSender;
import com.killkompany.bookshelf.utils.RxResponseWrappers;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LoginModel extends BaseModel<LoginView, LoginViewHolder> implements Observer<Integer>{

    public LoginModel(LoginView view) {
        super(view);
        stageChecker().observeOn(AndroidSchedulers.mainThread()).subscribe(this);
    }

    public Observable<UserWrapper> createUser(User user){
        return Observable.fromCallable(RequestSender.createUser(user.toMap()))
                .map(new Function<String, UserWrapper>() {
                    @Override
                    public UserWrapper apply(@NonNull String s) throws Exception {
                        Log.d("test"," createUser "+s);
                        return RxResponseWrappers.proceedResponseOrException(s, UserWrapper.class);
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    public Observable<User> checkUser(){
        return Observable.fromCallable(RequestSender.checkUser())
                .map(new Function<String, User>() {
                    @Override
                    public User apply(@NonNull String s) throws Exception {
                        Log.d("test"," checkUser "+s);
                        return RxResponseWrappers.proceedResponseOrException(s, UserWrapper.class).getUser();
                    }
                }).subscribeOn(Schedulers.newThread());
    }

    public Observable<Integer> stageChecker(){
       return Observable.fromCallable(RequestSender.checkUser()).subscribeOn(Schedulers.newThread())
                .map(new Function<String, UserWrapper>() {
                    @Override
                    public UserWrapper apply(@NonNull String s) throws Exception {
                        Log.d("test","stageChecker "+s);
//                        Gson gson = new Gson();
//                        ResponseWrapper<UserWrapper> response = gson.fromJson(s, new TypeToken<ResponseWrapper<UserWrapper>>(){}.getType());
//                        if(response.hasError()) throw new ResponseErrorException(response.getError());
//                        return response.getResult();
                        return RxResponseWrappers.proceedResponseOrException(s, UserWrapper.class);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).map(new Function<UserWrapper, Integer>() {
                   @Override
                   public Integer apply(@NonNull UserWrapper user) throws Exception {
                       if (user == null) return LoginView.STAGE_HAS_NO_ACCOUNT;
                       String formatText = view.getContext().getString(R.string.login_as_pattern);
                       Log.d("test", "user "+user.getUser().getName());
                       view.getViewHolder().loginAs.setText(String.format(formatText, user.getUser().getName()));
                       return LoginView.STAGE_HAS_ACCOUNT;
                   }
               });
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Integer integer) {
        Log.d("test", "onNext "+integer);
        view.prepareStage(integer);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d("test ",e.getMessage()+" "+ Arrays.toString(e.getStackTrace()));
    }

    @Override
    public void onComplete() {

    }
}
