package com.killkompany.bookshelf.mvp.presenters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.killkompany.bookshelf.mvp.models.BaseModel;
import com.killkompany.bookshelf.mvp.viewholders.BaseViewHolder;
import com.killkompany.bookshelf.mvp.views.BaseView;


public abstract class BasePresenter<V extends BaseView<VH>, M extends BaseModel<V, VH>, VH extends BaseViewHolder> {

    final M model;

    final V view;

    public BasePresenter(V view, M model){
        this.view = view;
        this.model = model;
    }

    public abstract void onCreate(Activity activity, Bundle savedInstance);

    public abstract void onResume();

    public abstract void onDestroy();

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getRootView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
