package com.killkompany.bookshelf.persistence;


import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorage {

    private static final String DIR_NAME = "book_images";

    public static String saveImageToInternalStorage(Context context, Bitmap image, String name){

        File directory = context.getDir(DIR_NAME, Context.MODE_PRIVATE);

        File newImage = new File(directory, name);

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(newImage);
            image.compress(Bitmap.CompressFormat.PNG, 75, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage.getAbsolutePath();

    }

}
