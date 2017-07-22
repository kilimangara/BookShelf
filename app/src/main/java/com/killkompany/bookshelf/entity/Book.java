package com.killkompany.bookshelf.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Book extends RealmObject implements Parcelable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("author")
    String author;

    @SerializedName("image")
    String imageUrl;

    @SerializedName("description")
    String description;

    @SerializedName("read_at")
    String readAt;

    public Book(){

    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        readAt = in.readString();
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
        parcel.writeString(imageUrl);
        parcel.writeString(description);
        parcel.writeString(readAt);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReadAt() {
        return readAt;
    }

    public void setReadAt(String read_at) {
        this.readAt = read_at;
    }
}
