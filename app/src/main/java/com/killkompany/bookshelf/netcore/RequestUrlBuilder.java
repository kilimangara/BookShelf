package com.killkompany.bookshelf.netcore;

import android.net.Uri;

public class RequestUrlBuilder {

    private static final String BASE_URL = "http://46.101.218.49:9997/api";

    private static final String BOOKS_SEGMENT = "books";

    private static final String USERS_SEGMENT = "users";

    private static final String IMAGE_SEGMENT = "image";

    private static final String HOST = "http://46.101.218.49/upload_image";

    public static final String QUERY_PAR="query";

    public static final String LIMIT_PAR = "limit";

    public static final String OFFSET_PAR = "offset";

    public static Uri buildBookUrl(){
        return Uri.parse(BASE_URL).buildUpon().appendPath(BOOKS_SEGMENT).build();
    }

    public static Uri buildSearchBookUrl(String query, int limit, int offset){
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon().appendPath(BOOKS_SEGMENT).appendQueryParameter(LIMIT_PAR, String.valueOf(limit))
                .appendQueryParameter(OFFSET_PAR, String.valueOf(offset));
        return query.length() == 0 ? builder.build() : builder.appendQueryParameter(QUERY_PAR, query).build();
    }

    public static Uri appenQueryToUrl(Uri baseUri, String query){
        return baseUri.buildUpon().appendQueryParameter(QUERY_PAR, query).build();
    }

    public static Uri appendLimitOffset(Uri baseUri, int limit, int offset){
        return baseUri.buildUpon().appendQueryParameter(LIMIT_PAR, String.valueOf(limit))
                .appendQueryParameter(OFFSET_PAR, String.valueOf(offset)).build();
    }

    public static Uri buildUserUrl(){
        return Uri.parse(BASE_URL).buildUpon().appendPath(USERS_SEGMENT).build();
    }

    public static Uri buildImageUrl(){
        return Uri.parse(HOST);
    }

}
