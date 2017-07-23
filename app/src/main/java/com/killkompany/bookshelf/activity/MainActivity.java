package com.killkompany.bookshelf.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.killkompany.bookshelf.R;
import com.killkompany.bookshelf.mvp.models.BooksModel;
import com.killkompany.bookshelf.mvp.presenters.BooksPresenter;
import com.killkompany.bookshelf.mvp.views.BooksView;


public class MainActivity extends AppCompatActivity {

    private BooksPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = this.getLayoutInflater().inflate(R.layout.layout_main, null);
        setContentView(rootView);
        BooksView view = new BooksView(rootView);
        BooksModel model = new BooksModel(view);
        presenter = new BooksPresenter(view, model);
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
