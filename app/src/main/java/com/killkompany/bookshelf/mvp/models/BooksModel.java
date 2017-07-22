package com.killkompany.bookshelf.mvp.models;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.killkompany.bookshelf.adapters.BookAdapter;
import com.killkompany.bookshelf.entity.Book;
import com.killkompany.bookshelf.entity.Books;
import com.killkompany.bookshelf.entity.ResponseWrapper;
import com.killkompany.bookshelf.exceptions.ResponseErrorException;
import com.killkompany.bookshelf.mvp.viewholders.BooksViewHolder;
import com.killkompany.bookshelf.mvp.views.BooksView;
import com.killkompany.bookshelf.netcore.RequestSender;
import com.killkompany.bookshelf.utils.GridSpacingItemDecorator;
import com.killkompany.bookshelf.utils.RecyclerPagination;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class BooksModel extends BaseModel<BooksView, BooksViewHolder> {

    private final BookAdapter adapter;

    private Observable<Object> scrollEvents;

    public BooksModel(final BooksView view) {
        super(view);
        adapter = new BookAdapter();
        view.getViewHolder().recyclerView.addItemDecoration(new GridSpacingItemDecorator(3,10,true));
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3);
        view.getViewHolder().recyclerView.setLayoutManager(gridLayoutManager);
        view.getViewHolder().recyclerView.setAdapter(adapter);
        scrollEvents = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Object> e) throws Exception {
                view.getViewHolder().recyclerView.addOnScrollListener(new RecyclerPagination(gridLayoutManager) {
                    @Override
                    protected void loadNextPage() {
                        e.onNext(new Object());
                    }

                    @Override
                    public int getCurrentPage() {
                        return adapter.getCurrentPage();
                    }

                    @Override
                    public boolean isLastPage() {
                        return adapter.isLastPage();
                    }
                });
            }
        });
    }

    public BookAdapter getAdapter(){
        return this.adapter;
    }

    public Observable<Object> scrollEvents(){
        return scrollEvents;
    }

    public Observable<Books> getBooks(int limit, int offset){
        return Observable.fromCallable(RequestSender.searchBooks("", limit, offset)).map(new Function<String, Books>() {
            @Override
            public Books apply(@NonNull String s) throws Exception {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ResponseWrapper<Books> responseWrapper = gson.fromJson(s, new TypeToken<ResponseWrapper<Books>>(){}.getType());
                if(responseWrapper.getResult() == null) throw new ResponseErrorException(responseWrapper.getError());
                return responseWrapper.getResult();
            }
        });
    }

    public Observable<Books> searchBooks(String query, int limit, int offset){
        return Observable.fromCallable(RequestSender.searchBooks(query, limit, offset)).map(new Function<String, Books>() {
            @Override
            public Books apply(@NonNull String s) throws Exception {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ResponseWrapper<Books> responseWrapper = gson.fromJson(s, new TypeToken<ResponseWrapper<Books>>(){}.getType());
                if(responseWrapper.getResult() == null) throw new ResponseErrorException(responseWrapper.getError());
                return responseWrapper.getResult();
            }
        });
    }
}
