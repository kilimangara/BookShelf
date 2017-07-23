package com.killkompany;

import android.app.Application;
import android.provider.Settings.Secure;
import android.util.Log;

import com.killkompany.bookshelf.persistence.DbHandler;
import com.killkompany.bookshelf.persistence.SharedPreferencesWork;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbHandler.getInstance().init(this);
        SharedPreferencesWork.getInstance().init(this);
        String id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        Log.d("test", id);
        SharedPreferencesWork.getInstance().setUserId(id);
    }
}
