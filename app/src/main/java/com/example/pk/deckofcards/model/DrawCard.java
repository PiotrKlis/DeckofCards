package com.example.pk.deckofcards.model;

/**
 * Created by PK on 06.08.2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrawCard {

    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("cards")
    @Expose
    private List<Card> cards = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("deck_id")
    @Expose
    private String deckId;



    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

}
