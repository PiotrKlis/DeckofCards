package com.example.pk.deckofcards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by PK on 06.08.2017.
 *
 * Class which downloads card images
 */

 class ImgDownloader extends AsyncTask<String, String, Bitmap> {

    private Bitmap image;

    @Override
    protected Bitmap doInBackground(String... params) {

        try {
            java.net.URL url = new java.net.URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            image = BitmapFactory.decodeStream(input);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}