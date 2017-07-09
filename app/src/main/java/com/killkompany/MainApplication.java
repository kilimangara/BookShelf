package com.killkompany;

import android.app.Application;

import com.killkompany.bookshelf.persistence.DbHandler;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbHandler.getInstance().init(this);
    }
}
