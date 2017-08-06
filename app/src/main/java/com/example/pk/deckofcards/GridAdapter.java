package com.example.pk.deckofcards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pk.deckofcards.model.Card;
import com.example.pk.deckofcards.model.DrawCard;

/**
 * Created by PK on 06.08.2017.
 */

public class GridAdapter extends BaseAdapter {

    private final Context mContext;
    private final DrawCard[] drawCards;

    GridAdapter(Context context, DrawCard[] cards) {
        this.mContext = context;
        this.drawCards = cards;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
