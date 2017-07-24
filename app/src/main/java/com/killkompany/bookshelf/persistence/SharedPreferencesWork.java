package com.killkompany.bookshelf.persistence;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesWork {

    private static SharedPreferencesWork instance;

    private final String PREF_NAME = "BOOKSHELF";

    private final String USER_ID = "USER_ID";

    private final String IS_AUTH = "IS_AUTH";

    private SharedPreferences prefs;

    public static SharedPreferencesWork getInstance(){
        if( instance == null){
            instance = new SharedPreferencesWork();
        }
        return instance;
    }

    public void init(Context context){
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUserId(String id){
        prefs.edit().putString(USER_ID, id).apply();
    }

    public String getUserId(){
        return prefs.getString(USER_ID, "");
    }

    public void authenticated(){
        prefs.edit().putBoolean(IS_AUTH, true).apply();
    }

    public boolean isAuthenticated(){
        return prefs.getBoolean(IS_AUTH, false);
    }
}
