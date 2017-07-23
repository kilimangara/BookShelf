package com.killkompany.bookshelf.mvp.presenters;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.killkompany.bookshelf.entity.Books;
import com.killkompany.bookshelf.mvp.models.BooksModel;
import com.killkompany.bookshelf.mvp.viewholders.BooksViewHolder;
import com.killkompany.bookshelf.mvp.views.BooksView;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BooksPresenter extends BasePresenter<BooksView, BooksModel, BooksViewHolder> {

    private static final int LIMIT = 10;

    private CompositeDisposable disposable;

    public BooksPresenter(BooksView view, BooksModel model) {
        super(view, model);
    }

    @Override
    public void onCreate(Activity activity, Bundle savedInstance) {
        disposable = new CompositeDisposable();
        view.getViewHolder().showLoading();
        model.getBooks(LIMIT, LIMIT*model.getAdapter().getCurrentPage()).retry(3).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Books>() {
            @Override
            public void accept(@NonNull Books books) throws Exception {
                if(books.getList() != null && books.getList().size() != 0) {
                    model.getAdapter().setBooks(books.getList());
                    view.getViewHolder().progressBar.setVisibility(View.GONE);
                    view.getViewHolder().recyclerView.setVisibility(View.VISIBLE);
                } else {
                    view.getViewHolder().progressBar.setVisibility(View.GONE);
                    view.getViewHolder().placeHolder.setVisibility(View.VISIBLE);
                }
            }
        }).subscribeOn(Schedulers.newThread()).subscribe();
        Disposable disposable = model.scrollEvents().debounce(500, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .switchMap(new Function<Object, ObservableSource<Books>>() {
                    @Override
                    public ObservableSource<Books> apply(@NonNull Object o) throws Exception {
                        return model.getBooks(LIMIT, LIMIT*model.getAdapter().getCurrentPage()).subscribeOn(Schedulers.newThread());
                    }
                }).doOnNext(new Consumer<Books>() {
                    @Override
                    public void accept(@NonNull Books books) throws Exception {
                        if(books.getList() != null && books.getList().size() != 0) {
                            model.getAdapter().setBooks(books.getList());
                        }
                    }
                }).subscribe();
        this.disposable.add(disposable);
    }

    @Override
    public void onResume() {
        hideKeyboard();
    }

    @Override
    public void onDestroy() {
       if(disposable != null && !disposable.isDisposed()) disposable.dispose();
    }
}
