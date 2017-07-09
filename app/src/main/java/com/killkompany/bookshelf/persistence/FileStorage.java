package com.killkompany.bookshelf.persistence;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;

public class FileStorage {

    private static final String DIR_NAME = "book_images";

    public static Callable<String> saveImageToInternalStorage(Context context,final Bitmap image, final String name){
        final File directory = context.getDir(DIR_NAME, Context.MODE_PRIVATE);
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                File newImage = new File(directory, name);
                FileOutputStream fos = new FileOutputStream(newImage);
                image.compress(Bitmap.CompressFormat.PNG, 70, fos);
                fos.close();
                return directory.getAbsolutePath();
            }
        };

    }

    public static Callable<Bitmap> loadImageByPath(final String path, final String name){
        return new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                File image = new File(path, name);
                return BitmapFactory.decodeStream(new FileInputStream(image));
            }
        };
    }



}
