package com.killkompany.bookshelf.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikitazlain on 25.07.17.
 */

public class UserWrapper {

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }
}
