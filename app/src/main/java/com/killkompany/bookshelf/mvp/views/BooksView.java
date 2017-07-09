package com.killkompany.bookshelf.mvp.views;

import android.view.View;

import com.killkompany.bookshelf.mvp.viewholders.BooksViewHolder;
import com.killkompany.bookshelf.utils.RxBindings;

import io.reactivex.Observable;

public class BooksView extends BaseView<BooksViewHolder> {

    public BooksView(View rootView) {
        super(rootView, BooksViewHolder.class);
    }

    public Observable<String> getSearchViewQuery(){
        return RxBindings.fromSearchView(viewHolder.searchView);
    }

}
