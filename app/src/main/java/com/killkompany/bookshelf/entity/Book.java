package com.killkompany.bookshelf.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;


public class Book extends RealmObject implements Parcelable {

    public static final int BOOK_NOT_STARTED = 0;

    public static final int BOOK_IN_PROGRESS = 1;

    public static final int BOOK_FINISHED = 2;

    String name;

    String author;

    int pages;

    int progressType;

    long created_at;

    public Book(){

    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        pages = in.readInt();
        progressType = in.readInt();
        created_at = in.readLong();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeInt(pages);
        parcel.writeInt(progressType);
        parcel.writeLong(created_at);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getProgressType() {
        return progressType;
    }

    public void setProgressType(int progressType) {
        this.progressType = progressType;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
