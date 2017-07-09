package com.killkompany.bookshelf.entity;

import io.realm.RealmObject;


public class Book extends RealmObject {

    public static final int BOOK_NOT_STARTED = 0;

    public static final int BOOK_IN_PROGRESS = 1;

    public static final int BOOK_FINISHED = 2;

    String name;

    String author;

    int pages;

    int progressType;

    long created_at;

}
