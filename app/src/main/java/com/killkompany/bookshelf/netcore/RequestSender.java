package com.killkompany.bookshelf.netcore;

import com.killkompany.bookshelf.persistence.SharedPreferencesWork;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.killkompany.bookshelf.netcore.RequestUrlBuilder.buildBookUrl;
import static com.killkompany.bookshelf.netcore.RequestUrlBuilder.buildSearchBookUrl;
import static com.killkompany.bookshelf.netcore.RequestUrlBuilder.buildUserUrl;

public class RequestSender {

    private static final String AUTH_HEADER = "Authorization";

    private static final OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).build();

    public static Callable<String> searchBooks(String query, int limit, int offset){
        final Request request = new Request.Builder().url(buildSearchBookUrl(query, limit, offset).toString())
                .addHeader(AUTH_HEADER, SharedPreferencesWork.getInstance().getUserId()).build();
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return client.newCall(request).execute().body().string();
            }
        };
    }

    public static Callable<String> deleteBook(String id){
        final FormBody formBody = new FormBody.Builder().add("id", id).build();
        final Request request = new Request.Builder().url(buildBookUrl().toString())
                .addHeader(AUTH_HEADER, SharedPreferencesWork.getInstance().getUserId()).delete(formBody).build();
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return client.newCall(request).execute().body().string();
            }
        };
    }

    public static Callable<String> editBook(Map<String, String> changedData){
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for( String key: changedData.keySet()){
            bodyBuilder.add(key, changedData.get(key));
        }
        final Request request = new Request.Builder().url(buildBookUrl().toString())
                .addHeader(AUTH_HEADER, SharedPreferencesWork.getInstance().getUserId()).patch(bodyBuilder.build()).build();
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return client.newCall(request).execute().body().string();
            }
        };
    }

    public static Callable<String> createBook(Map<String, String> bookData){
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for( String key: bookData.keySet()){
            bodyBuilder.add(key, bookData.get(key));
        }
        final Request request = new Request.Builder().url(buildBookUrl().toString()).post(bodyBuilder.build())
                .addHeader(AUTH_HEADER, SharedPreferencesWork.getInstance().getUserId()).build();
        return new Callable<String>(){
            @Override
            public String call() throws Exception {
                return client.newCall(request).execute().body().string();
            }
        };
    }

    public static Callable<String> createUser(Map<String, String> userData){
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        for (String key: userData.keySet()){
            bodyBuilder.add(key, userData.get(key));
        }
        final Request request = new Request.Builder().url(buildUserUrl().toString()).post(bodyBuilder.build())
                .addHeader(AUTH_HEADER, SharedPreferencesWork.getInstance().getUserId()).build();
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return client.newCall(request).execute().body().string();
            }
        };
    }

    

}
