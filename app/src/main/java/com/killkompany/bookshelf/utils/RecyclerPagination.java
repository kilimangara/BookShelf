package com.killkompany.bookshelf.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public abstract class RecyclerPagination extends RecyclerView.OnScrollListener{

    GridLayoutManager manager;

    public RecyclerPagination(GridLayoutManager linearLayoutManager){
        manager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = manager.getChildCount();
        int totalItemCount = manager.getItemCount();
        int firstVisiblePos = manager.findFirstVisibleItemPosition();
        Log.d("test","visible items "+visibleItemCount+" totalItemCount "+totalItemCount+" firstVisiblePos "+firstVisiblePos);
        if(!isLastPage()){
            if(visibleItemCount + firstVisiblePos >= totalItemCount && totalItemCount >= 0){
                loadNextPage();
                Log.d("test","loadNextPage");
            }
        }
    }

    protected abstract void loadNextPage();

    public abstract int getCurrentPage();

    public abstract boolean isLastPage();

}
