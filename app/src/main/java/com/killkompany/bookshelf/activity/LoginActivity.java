package com.killkompany.bookshelf.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.killkompany.bookshelf.R;
import com.killkompany.bookshelf.mvp.models.LoginModel;
import com.killkompany.bookshelf.mvp.presenters.LoginPresenter;
import com.killkompany.bookshelf.mvp.views.LoginView;


public class LoginActivity extends AppCompatActivity {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.layout_login, null);
        setContentView(rootView);
        LoginView view = new LoginView(rootView);
        LoginModel model = new LoginModel(view);
        presenter = new LoginPresenter(view, model);
        presenter.onCreate(this, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
