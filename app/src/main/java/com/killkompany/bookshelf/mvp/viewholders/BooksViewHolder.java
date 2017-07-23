package com.killkompany.bookshelf.mvp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.killkompany.bookshelf.R;
import com.killkompany.bookshelf.customviews.CustomToolbar;

import butterknife.BindView;

public class BooksViewHolder extends BaseViewHolder {

    @BindView(R.id.toolbar)
    public CustomToolbar toolbar;

    @BindView(R.id.search_view)
    public SearchView searchView;

    @BindView(R.id.rv_books)
    public RecyclerView recyclerView;

    @BindView(R.id.have_no_books)
    public TextView placeHolder;

    @BindView(R.id.progress_bar)
    public ProgressBar progressBar;

    public BooksViewHolder(View rootView) {
        super(rootView);
        toolbar.setMenu(R.menu.menu_main);
    }

    public void showPlaceholder(){
        recyclerView.setVisibility(View.GONE);
        placeHolder.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void showLoading(){
        recyclerView.setVisibility(View.GONE);
        placeHolder.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void reset(){
        recyclerView.setVisibility(View.VISIBLE);
        placeHolder.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

}
