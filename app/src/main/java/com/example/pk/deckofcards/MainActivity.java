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
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk.deckofcards.model.Card;
import com.example.pk.deckofcards.model.Deck;
import com.example.pk.deckofcards.model.DrawCard;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    Spinner spinner;
    Button btnGenerateDecks;
    Button btnDrawCards;
    TextView txtScore;
    TextView txtStaticScore;
    GridView gridView;
    ProgressBar progressBar;

    RetrofitInterface jsonGetDecks;
    RetrofitInterface jsonGetCards;
    RetrofitInterface jsonReshuffle;

    String deckId;
    DrawCard cardList;
    ArrayList<String> stringArrayList;

    // Default number of decks to generate
    int numberOfDecks = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringArrayList = new ArrayList<>();

        spinner = (Spinner) findViewById(R.id.spinner);
        btnGenerateDecks = (Button) findViewById(R.id.btnGenerateDecks);
        btnDrawCards = (Button) findViewById(R.id.btnDrawCards);
        txtScore = (TextView) findViewById(R.id.txtScore);
        gridView = (GridView) findViewById(R.id.gridView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtStaticScore = (TextView) findViewById(R.id.txtStaticScore);

        // Choose how many decks spinner

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

                progressBar.setVisibility(View.VISIBLE);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        jsonGetDecks = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                        Call<Deck> deckCall = jsonGetDecks.getDecks(numberOfDecks);

                        deckCall.enqueue(new Callback<Deck>() {
                            @Override
                            public void onResponse(Call<Deck> call, Response<Deck> response) {
                                deckId = response.body().getDeckId();
                                Log.i(TAG, "Deck id: " + deckId);
                                Toast.makeText(getApplicationContext(), "Talia została wygenerowana",
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                btnDrawCards.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onFailure(Call<Deck> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Wystąpił problem z połączeniem",
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);

                            }
                        });
                    }
                });
            }
        });

        btnDrawCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                stringArrayList.clear();

                jsonGetCards = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                Call<DrawCard> cardCall = jsonGetCards.getCards(deckId);

                cardCall.enqueue(new Callback<DrawCard>() {
                    @Override
                    public void onResponse(Call<DrawCard> call, Response<DrawCard> response) {

                        cardList = response.body();
                        ArrayList<Bitmap> imagesArray = new ArrayList<>();

                        // Download images

                        for (Card card : cardList.getCards()) {

                            stringArrayList.add(card.getCode());

                            Bitmap image;

                            try {
                                image = new ImgDownloader().execute(card.getImage()).get();
                                imagesArray.add(image);
                                txtStaticScore.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }


                        cardSchemas(stringArrayList);

                        // Fill gridView with images


                        GridAdapter gridAdapter = new GridAdapter(MainActivity.this,
                                R.layout.grid_row, imagesArray);
                        gridView.setAdapter(gridAdapter);



                        if (cardList.getRemaining() < 5) {

                            Toast.makeText(getApplicationContext(), "Ostatnie karty z talii. " +
                                    "Następuje przetasowanie.", Toast.LENGTH_SHORT).show();

                            jsonReshuffle = RetrofitInterface.retrofit.create(RetrofitInterface.class);
                            Call<Deck> reshuffleDeckCall = jsonReshuffle.getReshuffle(deckId);

                            reshuffleDeckCall.enqueue(new Callback<Deck>() {
                                @Override
                                public void onResponse(Call<Deck> call, Response<Deck> response) {

                                }

                                @Override
                                public void onFailure(Call<Deck> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Wystąpił " +
                                            "problem " + "z połączeniem, karty nie " +
                                            "zostały przetasowane", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        //txtStaticScore.setVisibility(View.VISIBLE);
                        //progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onFailure(Call<DrawCard> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Wystąpił problem z połączeniem",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * Method which checks cards for given conditions and updates the Score TextView
     *
     * @param strings list of cards
     */

    public void cardSchemas(ArrayList<String> strings) {

        ArrayList<String> valueArrayStrings = new ArrayList<>();
        ArrayList<Integer> valueArrayInts;
        ArrayList<String> suitArray = new ArrayList<>();
        String resultScore;

        valueArrayStrings.clear();
        suitArray.clear();

        // Seperate colors and values

        for (String string : strings) {
            String value = string.substring(0, 1);
            valueArrayStrings.add(value);
            String suit = string.substring(1, 2);
            suitArray.add(suit);
        }

        Schemas schemas = new Schemas();

        // Change String values into Ints
        valueArrayInts = schemas.changeValuesToInts(valueArrayStrings);

        // Check for "Stairs" and update result String
        resultScore = schemas.stairs(valueArrayInts);

        // Check for "Twins" and update result String
        String resultTwins = schemas.twins(valueArrayInts);
        if (!resultTwins.equals("") && !resultScore.equals("")) {
            resultScore = resultScore + ", " + resultTwins;
        } else {
            resultScore = resultScore + resultTwins;
        }

        // Check for "Figures" and update result String
        String resultFigures = schemas.figures(valueArrayStrings);
        if (!resultFigures.equals("") && !resultScore.equals("")) {
            resultScore = resultScore + ", " + resultFigures;
        } else {
            resultScore = resultScore + resultFigures;
        }

        // Check for "Color" and update result String
        String resultColor = schemas.color(suitArray);
        if (!resultColor.equals("") && !resultScore.equals("")) {
            resultScore = resultScore + ", " + resultColor;
        } else {
            resultScore = resultScore + resultColor;
        }

        txtScore.setText(resultScore);
    }
}