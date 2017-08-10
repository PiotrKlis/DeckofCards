package com.example.pk.deckofcards;

/**
 * Created by PK on 05.08.2017.
 */

import com.example.pk.deckofcards.model.Card;
import com.example.pk.deckofcards.model.Deck;
import com.example.pk.deckofcards.model.DrawCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

 interface RetrofitInterface {

    @GET("new/shuffle")
    Call<Deck> getDecks(@Query("deck_count") int count);

    @GET("{deck_id}/draw/?count=5")
    Call<DrawCard> getCards(@Path("deck_id") String id);

    @GET("{deck_id}/shuffle/")
    Call<Deck> getReshuffle(@Path("deck_id") String id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://deckofcardsapi.com/api/deck/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
