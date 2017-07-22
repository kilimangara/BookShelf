package com.killkompany.bookshelf.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Books {

    @SerializedName("data")
    private List<Books> list;

    @SerializedName("total_count")
    private int totalCount;

    public List<Books> getList() {
        return list;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
