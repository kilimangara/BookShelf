package com.killkompany.bookshelf.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.killkompany.bookshelf.R;
import com.killkompany.bookshelf.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> books;

    private int page = 0;

    private boolean isLastPage = false;

    public BookAdapter(){
        books = new ArrayList<>();
    }

    public void setBooks(List<Book> books){
        this.books = books;
        page = 1;
        notifyDataSetChanged();
    }

    public void resetAll(){
        this.books = new ArrayList<>();
        page = 0;
        notifyDataSetChanged();
    }

    public void appendBooks(List<Book> books){
        Log.d("test", "size of books "+books.size());
        if(books.size() == 0){
            isLastPage = true;
            return;
        }
        page++;
        int currentNum = this.books.size()-1;
        this.books.addAll(books);
        notifyItemRangeInserted(currentNum, books.size());
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        final int clickPos = holder.getAdapterPosition();
        Book book = books.get(position);
        holder.nameBook.setText(book.getName());
        Glide.with(holder.itemView).load(book.getImageUrl()).into(holder.imageBook);
    }

    public int getCurrentPage(){
        return this.page;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    protected class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView imageBook;

        TextView nameBook;

        Button buttonRead;

        public BookViewHolder(View itemView) {
            super(itemView);
            imageBook = (ImageView) itemView.findViewById(R.id.img_book);
            nameBook = (TextView) itemView.findViewById(R.id.name_book);
            buttonRead = (Button) itemView.findViewById(R.id.read_button);
        }
    }
}
