package com.killkompany.bookshelf.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Books {

    @SerializedName("data")
    private List<Book> list;

    @SerializedName("total_count")
    private int totalCount;

    public List<Book> getList() {
        return list;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
