package com.iteso.giovanni.pontepedo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                    break;
                }
            } while (c.moveToNext());
        }
        return g;
    }

    public Game getGame(String name) {
        Cursor c = getGamesCursor();
        Game g = new Game();
        if (c != null && c.moveToFirst()) {
            do {
                if(c.getString(1).equals(name)) {
                    g.setId(c.getInt(0));
                    g.setName(c.getString(1));
                    g.setDescription(c.getString(2));
                    if(c.getInt(3) == 1)
                        g.setChecked(true);
                    else
                        g.setChecked(false);
                    break;
                }
            } while (c.moveToNext());
        }
        return g;
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

    public CardGame getCardNumOfGame(String cardNum) {
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

    public Cursor getGamesPlayingCursor() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                DataBaseContract.GamesContract._ID,
                DataBaseContract.GamesContract.COLUMN_NAME,
                DataBaseContract.GamesContract.COLUMN_DESC,
                DataBaseContract.GamesContract.COLUMN_CHECKED
        };
        String selection =
                DataBaseContract.GamesContract.COLUMN_CHECKED + "=?";
        String args[] =
                {"1"};
        String sortOrder =
                DataBaseContract.GamesContract._ID + " ASC";
        Cursor c = db.query(
                DataBaseContract.GamesContract.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                sortOrder
        );
        return c;
    }

    public Cursor getGamesNotPlayingCursor() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                DataBaseContract.GamesContract._ID,
                DataBaseContract.GamesContract.COLUMN_NAME,
                DataBaseContract.GamesContract.COLUMN_DESC,
                DataBaseContract.GamesContract.COLUMN_CHECKED
        };
        String selection =
                DataBaseContract.GamesContract.COLUMN_CHECKED + "=?";
        String args[] =
                {"0"};
        String sortOrder =
                DataBaseContract.GamesContract._ID + " ASC";
        Cursor c = db.query(
                DataBaseContract.GamesContract.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                sortOrder
        );
        return c;
    }

    public void changeCardGame(int idOld, int idNew) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // UPDATE TABLE CARD GAMES SWAP
        ContentValues valuesCG = new ContentValues();
        valuesCG.put(DataBaseContract.CardGamesContract.COLUMN_IDGAME, idNew);
        String argsCG[] = {idOld + ""};
        db.update(DataBaseContract.CardGamesContract.TABLE_NAME,
                valuesCG,
                DataBaseContract.CardGamesContract.COLUMN_IDGAME + "=?",
                argsCG);

        // UPDATE TABLE GAMES - NEW GAME
        ContentValues valuesNew = new ContentValues();
        valuesNew.put(DataBaseContract.GamesContract.COLUMN_CHECKED, 1);
        String argsNew[] = {idNew + ""};
        db.update(DataBaseContract.GamesContract.TABLE_NAME,
                valuesNew,
                DataBaseContract.GamesContract._ID + "=?",
                argsNew);

        // UPDATE TABLE GAMES - OLD GAME
        ContentValues valuesOld = new ContentValues();
        valuesOld.put(DataBaseContract.GamesContract.COLUMN_CHECKED, 0);
        String argsOld[] = {idOld + ""};
        db.update(DataBaseContract.GamesContract.TABLE_NAME,
                valuesOld,
                DataBaseContract.GamesContract._ID + "=?",
                argsOld);
    }

    public void deleteGame(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String args[] = {id + ""};
        db.delete(DataBaseContract.GamesContract.TABLE_NAME,
                DataBaseContract.GamesContract._ID + "=?",
                args);
    }
}
