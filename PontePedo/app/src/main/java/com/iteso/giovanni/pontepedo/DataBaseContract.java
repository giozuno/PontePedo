package com.iteso.giovanni.pontepedo;

import android.provider.BaseColumns;

/**
 * Created by giovanni on 13/04/2016.
 */
public class DataBaseContract {
    public DataBaseContract() {
    }

    public static abstract class GamesContract implements BaseColumns {
        public static final String TABLE_NAME = "GAMES";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_CHECKED = "checked";
    }

    public static abstract class CardGamesContract implements BaseColumns {
        public static final String TABLE_NAME = "CARD_GAMES";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DRAWABLE = "drawable";
        public static final String COLUMN_IDGAME = "idGame";
    }
}
