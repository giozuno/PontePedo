package com.iteso.giovanni.pontepedo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by giovanni on 13/04/2016.
 */
public class DataBaseOperations {
    private DataBaseHelper dbHelper;

    public DataBaseOperations (Context context){
        dbHelper = new DataBaseHelper(context);
    }

    public long addGame(String name, String desc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.GamesContract.COLUMN_NAME, name);
        values.put(DataBaseContract.GamesContract.COLUMN_DESC, desc);
        values.put(DataBaseContract.GamesContract.COLUMN_CHECKED, 0);

        long newRowId = db.insert(
                DataBaseContract.GamesContract.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public Cursor getGamesCursor() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                DataBaseContract.GamesContract._ID,
                DataBaseContract.GamesContract.COLUMN_NAME,
                DataBaseContract.GamesContract.COLUMN_DESC,
                DataBaseContract.GamesContract.COLUMN_CHECKED
        };
        String sortOrder =
                DataBaseContract.GamesContract._ID + " ASC";
        Cursor c = db.query(
                DataBaseContract.GamesContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        return c;
    }

    public Game getGame(int id) {
        Cursor c = getGamesCursor();
        Game g = new Game();
        if (c != null && c.moveToFirst()) {
            do {
                if(c.getInt(0) == id) {
                    g.setId(c.getInt(0));
                    g.setName(c.getString(1));
                    g.setDescription(c.getString(2));
                    if(c.getInt(3) == 1)
                        g.setChecked(true);
                    else
                        g.setChecked(false);
                }
            } while (c.moveToNext());
        }
        return g;
    }

    public ArrayList<Game> getGameList() {
        Cursor c = getGamesCursor();
        ArrayList<Game> gameList = new ArrayList<>();
        if (c != null && c.moveToFirst()) {
            Game game;
            do {
                int i = 1;
                game = new Game();
                game.setId(c.getInt(0));
                game.setName(c.getString(1));
                game.setDescription(c.getString(2));
                if(c.getInt(3) == 1)
                    game.setChecked(true);
                else
                    game.setChecked(false);
                gameList.add(i, game);
                i++;
            } while (c.moveToNext());
        }
        return gameList;
    }

    public Cursor getCardsGameCursor() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                DataBaseContract.CardGamesContract._ID,
                DataBaseContract.CardGamesContract.COLUMN_NAME,
                DataBaseContract.CardGamesContract.COLUMN_DRAWABLE,
                DataBaseContract.CardGamesContract.COLUMN_IDGAME
        };
        String sortOrder =
                DataBaseContract.CardGamesContract._ID + " ASC";
        Cursor c = db.query(
                DataBaseContract.CardGamesContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        return c;
    }

    public CardGame getCardGame(String cardNum) {
        Cursor c = getCardsGameCursor();
        CardGame cg = new CardGame();
        if (c != null && c.moveToFirst()) {
            do {
                if(c.getString(1).equals(cardNum.toString())) {
                    cg.setId(c.getInt(0));
                    cg.setName(c.getString(1));
                    cg.setDrawable(c.getInt(2));
                    cg.setIdGame(c.getInt(3));
                }
            } while (c.moveToNext());
        }
        return cg;
    }
}
