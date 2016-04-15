package com.iteso.giovanni.pontepedo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;

/**
 * Created by giovanni on 15/04/2016.
 */
public class AdapterEditGame extends CursorAdapter {
    public AdapterEditGame(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_edit_game_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CheckBox gameNameCheckBox = (CheckBox) view.findViewById(R.id.item_edit_game_checkBox);

        String gameName = cursor.getString(1);

        gameNameCheckBox.setText(gameName);
    }

}