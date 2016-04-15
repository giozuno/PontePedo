package com.iteso.giovanni.pontepedo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by giovanni on 11/04/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PontePedo.db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_GAME =
            "CREATE TABLE " + DataBaseContract.GamesContract.TABLE_NAME + "(" +
                    DataBaseContract.GamesContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataBaseContract.GamesContract.COLUMN_NAME + " TEXT NOT NULL," +
                    DataBaseContract.GamesContract.COLUMN_DESC + " TEXT NOT NULL," +
                    DataBaseContract.GamesContract.COLUMN_CHECKED + " INTEGER NOT NULL)";

    public static final String CREATE_TABLE_CARD_GAMES =
            "CREATE TABLE " + DataBaseContract.CardGamesContract.TABLE_NAME + "(" +
                    DataBaseContract.CardGamesContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataBaseContract.CardGamesContract.COLUMN_NAME + " TEXT NOT NULL," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_IDGAME + " INTEGER NOT NULL," +
                    "FOREIGN KEY("+ DataBaseContract.CardGamesContract.COLUMN_IDGAME +") REFERENCES " +
                    DataBaseContract.CardGamesContract.TABLE_NAME + "("+ DataBaseContract.GamesContract._ID+"))";

    private static final String SQL_DELETE_GAME_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.GamesContract.TABLE_NAME;

    private static final String SQL_DELETE_CARD_GAMES_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.CardGamesContract.TABLE_NAME;

    public static final String DEFAULT_INSERT_GAMES = "insert into GAMES values" +
            "(null,\"Yo Nunca Nunca\",\"Este juego consiste en una ronda en la que cada participante dira una verdad la cual comenzara con la frase YO NUNCA NUNCA... Si alguien ha hecho dicha verdad debera tomar un trago.\",\"1\")," +
            "(null,\"Animales\",\"En la primera ronda cada participante hara el sonido de un animal sin mostrar los dientes e indicando que animal es, despues los participantes se llamaran entre si primero haciendo su ruido y despues el del compañero al que le hablan (ejemplo: Pollo PIO llamando a pato CUAK). Si alguien muestra los dientes en algun momento, pierde el juego.\",\"1\")," +
            "(null,\"Historia\",\"Este juego consiste en hacer una historia en la que cada jugador debe repetir la historia creada y agregarle una palabra más (ejemplo: Había --> Había una --> Había una vez). Si alguien se equivoca pierde.\",\"1\")," +
            "(null,\"Cascada\",\"Todos empiezan a tomar al mismo tiempo, pero solo la persona que saco la carta puede dejar de tomar cuando quiera, y solo cuando el se baje la persona de su derecha se podra bajar y asi sucesivamente hasta que todos dejen de tomar.\",\"1\")," +
            "(null,\"Palabra Prohibida\",\"El jugador que obtuvo esta carta prohibe una palabra, si algun jugador la dice debera tomar un trago, la palabra cambiara hasta que vuelva a salir esta carta.\",\"1\")," +
            "(null,\"Preguntas\",\"Este juego lo empezara el jugador que saco la carta, este le hara una pregunta al jugador que quiera, y el preguntado debera hacer otra pregunta a otro jugador diferente y asi sucesivamene. Si alguien tarda en hacer una pregunta o lo que contesta no es pregunta perdera el juego.\",\"1\")," +
            "(null,\"Mi Pinga - Mi Pepa\",\"Primero se jugara un basta para elegir una letra, despues cada jugador debera decir un adjetivo que empieze con la letra SIN MOSTRAR LOS DIENTES y dependiendo si es hombre (Mi Pinga), o mujer (Mi Pepa) diran su frase: (letra A) Mi pipa es asombrosa, Mi pepa es amable.\",\"1\")," +
            "(null,\"Dedo\",\"El jugador que obtenga esta carta debe de poner su dedo en un lugar visible (por ejemplo la mesa), esto sera en el momento que el quiera y el ultimo que ponga su dedo donde el lo puso sera el perdedor.\",\"1\")," +
            "(null,\"Discrepo\",\"El jugador que saca la carta debe de decir un verbo o una palabra cualquiera y el jugador de su derecha debe decir algo relacionado a esa palabra y asi sucesivamente, pero tu palabra solo debe de estar relacionada con la palabra anterior por ejemplo: Playa -- Arena -- Mar -- Sal -- Pimienta -- Comida (Pimienta no tiene relacion con arena ni mar, pero es valida por que si la tiene con sal), si algun jugador considera que una palabra no tiene relacion debera decir DISCREPO y si todos DISCREPAN el jugador perdera, si no todos discrepan el juego sigue.\",\"1\")," +
            "(null,\"Mesero\",\"El jugador girara una botella para elegir quien sera su mesero, el jugador que haya sido seleccionado debera ser su mesero hasta que esta carta vuelva a salir\",\"1\")," +
            "(null,\"Marcianos\",\"Los jugadores se enumeraran empezando por quien saco la carta, y el mismo comenzara SIN MOSTRAR LOS DIENTES y CON LAS MANOS EN LA CABEZA llamando a sus compañeros por ejemplo: Marcianito 1 (ya que el empieza) llamando a Marcianito 3. Todos los participantes pueden hacer ruidos para hacer reir a los demas y quien muestre los dientes pierde.\",\"1\")," +
            "(null,\"Caricachupas\",\"Es el clasico juego de caricaturas. Al ritmo de los aplausos cada jugador debe de mencionar un objeto que sea valido dentro del tema elejido (ejemplo: Caricaturas -- presenta -- nombres de -- TEMA A ELEGIR -- por ejemplo -- PRIMER OBJETO...). El primero en equivocarse pierde el juego.\",\"1\")," +
            "(null,\"El Abuelo\",\"Primero se deben de enumerar los concursantes del juego, y entre ellos se llamaran entre si usando la siguiente dinamica [ejemplo: Cuando el abuelo murio 4 copas de vino dejo -- (El numero 4 responde) ¿Y por que 4? -- (Vuelve a contestar el primero) ¿Entonces cuantas? -- 6 -- (Contesta el 6) ¿Y por que 6? ...] El juego sigue hasta que alguien se equivoque\",\"1\")";

    public static final String DEFAULT_INSERT_CARD_GAMES = "insert into CARD_GAMES values" +
            "(null,\"AsR\"," + R.drawable.card_a + ",\"1\")," +
            "(null,\"2R\"," + R.drawable.card_2 + ",\"2\")," +
            "(null,\"3R\"," + R.drawable.card_3 + ",\"3\")," +
            "(null,\"4R\"," + R.drawable.card_4 + ",\"4\")," +
            "(null,\"5R\"," + R.drawable.card_5 + ",\"5\")," +
            "(null,\"6R\"," + R.drawable.card_6 + ",\"6\")," +
            "(null,\"7R\"," + R.drawable.card_7 + ",\"7\")," +
            "(null,\"8R\"," + R.drawable.card_8 + ",\"8\")," +
            "(null,\"9R\"," + R.drawable.card_9 + ",\"9\")," +
            "(null,\"10R\"," + R.drawable.card_10 + ",\"10\")," +
            "(null,\"JR\"," + R.drawable.card_j + ",\"11\")," +
            "(null,\"QR\"," + R.drawable.card_q + ",\"12\")," +
            "(null,\"KR\"," + R.drawable.card_k + ",\"13\")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GAME);
        db.execSQL(CREATE_TABLE_CARD_GAMES);
        db.execSQL(DEFAULT_INSERT_GAMES);
        db.execSQL(DEFAULT_INSERT_CARD_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " +
        newVersion + ", wich will destroy all old data");
        db.execSQL("DROP TBALE IF IT EXISTS " + DataBaseContract.GamesContract.TABLE_NAME);
        db.execSQL("DROP TBALE IF IT EXISTS " + DataBaseContract.CardGamesContract.TABLE_NAME);
        onCreate(db);
    }
}
