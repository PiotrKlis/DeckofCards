package com.example.pk.deckofcards.model;

/**
 * Created by PK on 06.08.2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("svg")
    @Expose
    private String svg;
    @SerializedName("png")
    @Expose
    private String png;

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

}