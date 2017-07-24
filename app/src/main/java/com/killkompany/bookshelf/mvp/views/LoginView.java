package com.killkompany.bookshelf.mvp.views;

import android.view.View;

import com.killkompany.bookshelf.mvp.viewholders.LoginViewHolder;
import com.killkompany.bookshelf.utils.RxBindings;

import io.reactivex.Observable;


public class LoginView extends BaseView<LoginViewHolder> {

    public final static int STAGE_HAS_ACCOUNT = 0;

    public final static int STAGE_HAS_NO_ACCOUNT = 1;

    public LoginView(View rootView, int stage) {
        super(rootView, LoginViewHolder.class);
        prepareStage(stage);
    }

    public Observable<Object> getLoginAsClicks(){
        return RxBindings.fromButton(viewHolder.loginAs);
    }

    public Observable<Object> getLoginByClicks(){
        return RxBindings.fromButton(viewHolder.loginBy);
    }
    public Observable<Object> getRegisterClicks(){
        return RxBindings.fromButton(viewHolder.register);
    }

    public void prepareStage(int stage){
        switch (stage){
            case STAGE_HAS_ACCOUNT:
                viewHolder.loginAs.setVisibility(View.VISIBLE);
                viewHolder.loginBy.setVisibility(View.VISIBLE);
                viewHolder.register.setVisibility(View.GONE);
                break;
            case STAGE_HAS_NO_ACCOUNT:
                viewHolder.loginAs.setVisibility(View.GONE);
                viewHolder.loginBy.setVisibility(View.VISIBLE);
                viewHolder.register.setVisibility(View.VISIBLE);
                break;
        }
    }

}
