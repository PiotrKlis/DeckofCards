package com.example.pk.deckofcards;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.deckofcards.model.Card;
import com.example.pk.deckofcards.model.Deck;
import com.example.pk.deckofcards.model.DrawCard;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    Spinner spinner;
    Button btnGenerateDecks;
    Button btnDrawCards;
    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;
    TextView txtScore;

    GridLayout grid;

    RetrofitInterface jsonGetDecks;
    RetrofitInterface jsonGetCards;

    String deckId;
    DrawCard cardList;
    boolean reshuffle;

    // Default number of decks to generate

    int numberOfDecks = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        btnGenerateDecks = (Button) findViewById(R.id.btnGenerateDecks);
        btnDrawCards = (Button) findViewById(R.id.btnDrawCards);
        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);
        txtScore = (TextView) findViewById(R.id.txtScore);
        grid = (GridLayout) findViewById(R.id.gridLayout);

        // Populate the spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.decks_count, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                numberOfDecks = Integer.parseInt((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGenerateDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        jsonGetDecks = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                        Call<Deck> deckCall = jsonGetDecks.getDecks(numberOfDecks);

                        deckCall.enqueue(new Callback<Deck>() {
                            @Override
                            public void onResponse(Call<Deck> call, Response<Deck> response) {
                                deckId = response.body().getDeckId();
                                Log.i(TAG, deckId);


                            }

                            @Override
                            public void onFailure(Call<Deck> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Wystąpił problem z połączeniem", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });

        btnDrawCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        jsonGetCards = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                        Call<DrawCard> cardCall = jsonGetCards.getCards(deckId);

                        cardCall.enqueue(new Callback<DrawCard>() {
                            @Override
                            public void onResponse(Call<DrawCard> call, Response<DrawCard> response) {
                                cardList = response.body();

                                for (Card card : cardList.getCards()) {

                                    Bitmap image = null;
                                    try {
                                        image = new imageDownloader().execute(card.getImage()).get();
                                        card1.setImageBitmap(image);

                                    } catch (InterruptedException | ExecutionException e) {
                                        e.printStackTrace();
                                    }

                                    GridAdapter gridAdapter = new GridAdapter(this, card);

                                }

                                if (cardList.getRemaining() == 0) {
                                    reshuffle = true;
                                    Toast.makeText(getApplicationContext(), "Karty zostaną przetasowane przy kolejnym losowaniu", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<DrawCard> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Wystąpił problem z połączeniem", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }


}