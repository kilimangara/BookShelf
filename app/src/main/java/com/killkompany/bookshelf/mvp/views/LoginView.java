package com.killkompany.bookshelf.mvp.views;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.killkompany.bookshelf.mvp.viewholders.LoginViewHolder;
import com.killkompany.bookshelf.utils.RxBindings;

import io.reactivex.Observable;


public class LoginView extends BaseView<LoginViewHolder> {

    public final static int STAGE_HAS_ACCOUNT = 0;

    public final static int STAGE_HAS_NO_ACCOUNT = 1;

    public LoginView(View rootView) {
        super(rootView, LoginViewHolder.class);
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

    private void animateButton(Button button){
        Log.d("test","animate button with text "+button.getText());
        button.setVisibility(View.VISIBLE);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(button, "scaleX",1f);
        animator1.setDuration(700);
        animator1.setInterpolator(new OvershootInterpolator());
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(button, "scaleY",1f);
        animator2.setDuration(700);
        animator2.setInterpolator(new OvershootInterpolator());
        animator1.start();
        animator2.start();
    }

    public void prepareStage(int stage){
        animateButton(viewHolder.loginBy);
        switch (stage){
            case STAGE_HAS_ACCOUNT:
                animateButton(viewHolder.loginAs);
                break;
            case STAGE_HAS_NO_ACCOUNT:
                animateButton(viewHolder.register);
                break;
        }
    }

}
