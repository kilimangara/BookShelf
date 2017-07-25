package com.killkompany.bookshelf.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.killkompany.bookshelf.persistence.SharedPreferencesWork;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SharedPreferencesWork.getInstance().isAuthenticated()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
