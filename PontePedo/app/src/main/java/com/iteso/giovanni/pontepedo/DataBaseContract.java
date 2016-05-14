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
        public static final String COLUMN_DRAWABLE_0 = "drawable_0";
        public static final String COLUMN_DRAWABLE_1 = "drawable_1";
        public static final String COLUMN_DRAWABLE_2 = "drawable_2";
        public static final String COLUMN_DRAWABLE_3 = "drawable_3";
        public static final String COLUMN_DRAWABLE_4 = "drawable_4";
        public static final String COLUMN_DRAWABLE_5 = "drawable_5";
        public static final String COLUMN_DRAWABLE_6 = "drawable_6";
        public static final String COLUMN_DRAWABLE_7 = "drawable_7";
        public static final String COLUMN_DRAWABLE_8 = "drawable_8";
        public static final String COLUMN_IDGAME = "idGame";
    }
}
