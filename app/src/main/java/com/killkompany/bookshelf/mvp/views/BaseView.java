package com.killkompany.bookshelf.mvp.views;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.killkompany.bookshelf.mvp.viewholders.BaseViewHolder;

import java.lang.reflect.InvocationTargetException;


public abstract class BaseView<T extends BaseViewHolder> {

    public T viewHolder;

    private View rootView;

    public BaseView(View rootView, Class<T> tClass){
        this.rootView = rootView;
        try {
            this.viewHolder = tClass.getConstructor(View.class).newInstance(rootView);
        } catch (InstantiationException e) {
            Log.e("test","error");
        } catch (IllegalAccessException e) {
            Log.e("test","error");
        } catch (InvocationTargetException e) {
            Log.e("test","error");
        } catch (NoSuchMethodException e) {
            Log.e("test","error");
        }
    }

    public final T getViewHolder(){
        return viewHolder;
    }

    public final Context getContext(){
        return rootView.getContext();
    }

    public final View getRootView(){ return rootView;}
}
