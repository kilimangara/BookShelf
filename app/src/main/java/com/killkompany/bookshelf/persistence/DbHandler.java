package com.killkompany.bookshelf.persistence;

import android.content.Context;

import com.killkompany.bookshelf.entity.Book;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.operators.observable.ObservableEmpty;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class DbHandler {

    private static DbHandler instance;

    public static DbHandler getInstance(){
        if (instance == null) {
            instance = new DbHandler();
        }
        return instance;
    }


    public void init(Context context){
        Realm.init(context);
    }

    public void saveBook(Book book){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();
    }

    public List<Book> getAllBooks(){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Book.class).findAll();
    }

    public Observable<RealmResults<Book>> findBooks(String query){
        final String finalQuery = "*".concat(query).concat("*");
        return ObservableEmpty.create(new ObservableOnSubscribe<RealmResults<Book>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<RealmResults<Book>> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.where(Book.class).like("name", finalQuery).or().like("author", finalQuery).findAllAsync().addChangeListener(new RealmChangeListener<RealmResults<Book>>() {
                    @Override
                    public void onChange(RealmResults<Book> books) {
                        e.onNext(books);
                    }
                });
            }
        });
    }

}
