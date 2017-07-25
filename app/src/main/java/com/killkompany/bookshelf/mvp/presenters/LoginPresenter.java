package com.killkompany.bookshelf.mvp.presenters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.killkompany.bookshelf.activity.MainActivity;
import com.killkompany.bookshelf.mvp.models.LoginModel;
import com.killkompany.bookshelf.mvp.viewholders.LoginViewHolder;
import com.killkompany.bookshelf.mvp.views.LoginView;
import com.killkompany.bookshelf.persistence.SharedPreferencesWork;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class LoginPresenter extends BasePresenter<LoginView, LoginModel, LoginViewHolder> {

    private CompositeDisposable compositeDisposable;

    public LoginPresenter(LoginView view, LoginModel model) {
        super(view, model);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate(final Activity activity, Bundle savedInstance) {
        Disposable loginAs = view.getLoginAsClicks().doOnNext(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                SharedPreferencesWork.getInstance().authenticated();
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        }).subscribe();
        compositeDisposable.add(loginAs);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) compositeDisposable.dispose();
    }
}
