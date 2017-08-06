package com.example.pk.deckofcards.model;

/**
 * Created by PK on 05.08.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("suit")
    @Expose
    private String suit;
    @SerializedName("images")
    @Expose
    private Images images;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}
