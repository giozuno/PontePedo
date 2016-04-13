package com.iteso.giovanni.pontepedo;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by giovanni on 11/04/2016.
 */
public class DataBaseSource {

    private DataBaseHelper openHelper;
    private SQLiteDatabase database;

    public DataBaseSource(Context context){
        openHelper = new DataBaseHelper(context);
        database = openHelper.getWritableDatabase();
    }
}
