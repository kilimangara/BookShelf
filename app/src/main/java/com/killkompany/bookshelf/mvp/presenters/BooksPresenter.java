package com.killkompany.bookshelf.mvp.presenters;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.killkompany.bookshelf.entity.Books;
import com.killkompany.bookshelf.mvp.models.BooksModel;
import com.killkompany.bookshelf.mvp.viewholders.BooksViewHolder;
import com.killkompany.bookshelf.mvp.views.BooksView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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
        view.getViewHolder().refreshLayout.setRefreshing(true);
        //view.getViewHolder().showLoading();
//        model.getBooks(LIMIT, LIMIT*model.getAdapter().getCurrentPage()).retry(3).observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Books>() {
//            @Override
//            public void accept(@NonNull Books books) throws Exception {
//                if(books.getList() != null && books.getList().size() != 0) {
//                    model.getAdapter().setBooks(books.getList());
//                    view.getViewHolder().progressBar.setVisibility(View.GONE);
//                    view.getViewHolder().recyclerView.setVisibility(View.VISIBLE);
//                } else {
//                    view.getViewHolder().progressBar.setVisibility(View.GONE);
//                    view.getViewHolder().placeHolder.setVisibility(View.VISIBLE);
//                }
//            }
//        }).subscribeOn(Schedulers.newThread()).subscribe();
        Disposable disposable = model.scrollEvents().debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .switchMap(new Function<Object, ObservableSource<Books>>() {
                    @Override
                    public ObservableSource<Books> apply(@NonNull Object o) throws Exception {
                        Log.d("test","isLastPage "+model.getAdapter().isLastPage());
                        if( model.getAdapter().isLastPage()) return Observable.never();
                        return model.getBooks(LIMIT, LIMIT*model.getAdapter().getCurrentPage()).subscribeOn(Schedulers.newThread());
                    }
                }).doOnNext(new Consumer<Books>() {
                    @Override
                    public void accept(@NonNull Books books) throws Exception {
                            model.getAdapter().appendBooks(books.getList());
                    }
                }).subscribe();

        Disposable refresh = model.refreshEvents().switchMap(new Function<Object, ObservableSource<Books>>() {
            @Override
            public ObservableSource<Books> apply(@NonNull Object o) throws Exception {
                model.getAdapter().resetAll();
                return model.getBooks(LIMIT, LIMIT*model.getAdapter().getCurrentPage()).subscribeOn(Schedulers.newThread());
            }
        }).retry(3).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Books>() {
            @Override
            public void accept(@NonNull Books books) throws Exception {
                if(books.getList() != null && books.getList().size() != 0) {
                    model.getAdapter().setBooks(books.getList());
                    view.getViewHolder().refreshLayout.setVisibility(View.VISIBLE);
                    view.getViewHolder().placeHolder.setVisibility(View.GONE);
                } else {
                    view.getViewHolder().refreshLayout.setVisibility(View.GONE);
                    view.getViewHolder().placeHolder.setVisibility(View.VISIBLE);
                }
                view.getViewHolder().refreshLayout.setRefreshing(false);
            }
        }).subscribe();
        this.disposable.add(refresh);
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
