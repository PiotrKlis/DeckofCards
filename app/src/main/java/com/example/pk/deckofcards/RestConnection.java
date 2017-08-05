package com.example.pk.deckofcards;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by PK on 2017-08-05.
 */

public class RestConnection {

    public RestConnection() throws IOException {
    }

    URL shuffle = new URL("https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1");
    URL draw = new URL("https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2");
    URL reshuffle = new URL("https://deckofcardsapi.com/api/deck/<<deck_id>>/shuffle/");

    // Create connection
    HttpsURLConnection shuffleConnection = (HttpsURLConnection) shuffle.openConnection();
    HttpsURLConnection drawConnection = (HttpsURLConnection) draw.openConnection();
    HttpsURLConnection reshuffleConnection = (HttpsURLConnection) reshuffle.openConnection();

    if (myConnection.getResponseCode() == 200) {

        InputStreamReader responseBodyReader =
                new InputStreamReader(responseBody, "UTF-8");
        // Success
        // Further processing here
    } else {
        // Error handling code goes here
    }



}
