package com.iteso.giovanni.pontepedo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by giovanni on 13/04/2016.
 */
public class AdapterGame extends CursorAdapter {
    public AdapterGame(Context context, Cursor c, boolean autoRequery) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView gameNameTextView = (TextView) view.findViewById(R.id.item_game_name);

        String gameName = cursor.getString(1);

        gameNameTextView.setText(gameName);
    }

}
