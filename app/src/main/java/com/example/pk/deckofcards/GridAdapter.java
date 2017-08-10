package com.example.pk.deckofcards;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.pk.deckofcards.model.Card;
import com.example.pk.deckofcards.model.DrawCard;

import java.util.ArrayList;

/**
 * Created by PK on 06.08.2017.
 */

 class GridAdapter extends ArrayAdapter<Bitmap> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Bitmap> data = new ArrayList<>();

    GridAdapter(Context context, int layoutResourceId, ArrayList<Bitmap> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RecordHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, null);

            holder = new RecordHolder();
            holder.imageItem = (ImageView) row.findViewById(R.id.image_item);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Bitmap item = getItem(position);
        holder.imageItem.setImageBitmap(item);
        return row;
    }

    private static class RecordHolder {
        ImageView imageItem;
    }

}
