package com.killkompany.bookshelf.mvp.models;

import com.killkompany.bookshelf.mvp.viewholders.BooksViewHolder;
import com.killkompany.bookshelf.mvp.views.BooksView;


public class BooksModel extends BaseModel<BooksView, BooksViewHolder> {

    public BooksModel(BooksView view) {
        super(view);
    }
}
