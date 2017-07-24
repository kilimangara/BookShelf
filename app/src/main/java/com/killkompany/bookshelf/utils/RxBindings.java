package com.killkompany.bookshelf.utils;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;

public class RxBindings {

    public static Observable<String> fromSearchView(final SearchView searchView){
        Observable<String> observable =  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if(query.length() > 0){
                            e.onNext(query);
                            Log.d("test","sending to request "+query);
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                if(searchView.getQuery().length() >0) {
                    e.onNext(searchView.getQuery().toString());
                }
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d("test","doOnComplete");
                searchView.setOnQueryTextListener(null);
            }
        });
        return observable;
    }

    public static Observable<Object> fromButton(final Button button){
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Object> e) throws Exception {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        e.onNext(new Object());
                    }
                });
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                button.setOnClickListener(null);
            }
        });
    }
}
