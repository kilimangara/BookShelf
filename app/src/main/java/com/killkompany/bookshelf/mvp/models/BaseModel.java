package com.killkompany.bookshelf.mvp.models;

import android.content.Context;

import com.killkompany.bookshelf.mvp.viewholders.BaseViewHolder;
import com.killkompany.bookshelf.mvp.views.BaseView;

/**
 * Created by nikitazlain on 09.07.17.
 */

public abstract class BaseModel<T extends BaseView<R>, R extends BaseViewHolder> {

    public T view;

    public BaseModel(T view){
        this.view = view;
    }

    public Context getContext(){
        return view.getContext();
    }
}
