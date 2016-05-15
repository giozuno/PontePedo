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
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_0 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_1 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_2 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_3 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_4 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_5 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_6 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_7 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_8 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F1 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F2 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F3 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F4 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F5 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_DRAWABLE_F6 + " INTEGER," +
                    DataBaseContract.CardGamesContract.COLUMN_IDGAME + " INTEGER NOT NULL," +
                    "FOREIGN KEY("+ DataBaseContract.CardGamesContract.COLUMN_IDGAME +") REFERENCES " +
                    DataBaseContract.CardGamesContract.TABLE_NAME + "("+ DataBaseContract.GamesContract._ID+"))";

    private static final String SQL_DELETE_GAME_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.GamesContract.TABLE_NAME;

    private static final String SQL_DELETE_CARD_GAMES_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.CardGamesContract.TABLE_NAME;

    public static final String DEFAULT_INSERT_GAMES = "insert into GAMES values" +
            "(null,\"Yo Nunca Nunca\",\"Este juego consiste en una ronda en la que cada participante dira una accion la cual comenzara con la frase YO NUNCA NUNCA... Si alguien ha hecho dicha accion debera tomar un trago.\",\"1\")," +
            "(null,\"Animales\",\"En la primera ronda cada participante hara el sonido de un animal sin mostrar los dientes e indicando que animal es, despues los participantes se llamaran entre si primero haciendo su ruido y despues el del compañero al que le hablan (ejemplo: Pollo PIO llamando a pato CUAK). Si alguien muestra los dientes en algun momento, perdera el juego.\n\",\"1\")," +
            "(null,\"Historia\",\"Este juego consiste en hacer una historia en la que cada jugador debe repetir la historia creada y agregarle una palabra más (ejemplo: Había --> Había una --> Había una vez). Si alguien se equivoca pierde.\",\"1\")," +
            "(null,\"Cascada\",\"Todos empiezan a tomar al mismo tiempo, pero solo la persona que saco la carta puede dejar de tomar cuando el quiera, y solo una vez que el deje de tomar la persona de su derecha  podra detenerse y asi sucesivamente hasta que todos dejen de tomar.\n\",\"1\")," +
            "(null,\"Palabra Prohibida\",\"El jugador que obtuvo esta carta prohibe una palabra, y si algun jugador la dice debera tomar un trago. La palabra cambiara hasta que vuelva a salir esta carta.\",\"1\")," +
            "(null,\"Preguntas\",\"Este juego consiste en hacer puras preguntas. El jugador que saco la carta empezara y este debe realizar una pregunta a algun jugador, el preguntado debera realizar ahora otra pregunta a otro jugador y asi sucesivamente. Solo se vale hacer preguntas y estas pueden ser de cualquier cosa.\",\"1\")," +
            "(null,\"Mi Pinga - Mi Pepa\",\"Primero se jugara un basta para elegir una letra, despues cada jugador debera decir un adjetivo que empieze con la letra escogida, y SIN MOSTRAR LOS DIENTES, dependiendo si es hombre (Mi Pinga), o mujer (Mi Pepa) diran su frase. Ejemplo: (letra A) Mi pipa es asombrosa, Mi pepa es amable.\",\"1\")," +
            "(null,\"Dedo\",\"El jugador que obtenga esta carta debe de, en el momento que el lo decida, poner su dedo índice en un lugar visible para los demas jugadores, una vez que él lo haya puesto los demas jugadores deberan de hacer lo mismo, y él último en realizarlo sera el perdedor. El lugar donde se pondrá el dedo se debe de indicar cuando se obtiene la carta.\n\",\"1\")," +
            "(null,\"Discrepo\",\"Este juego consiste en decir palabras relacionadas. El juador que obtiene la carta debe decir una palabra cualquiera, despues el jugador de su derecha debera decir una palabra relacionada a la anterior y así sucesivamente. Pero cada palabra que se diga solo debe de tener relación con la palabra pasada, y si alguien considera lo contrario debe levantar su mano y decir DISCREPO. Si todos discrepan el jugador perdera el juego, sino este continuara. Ejemplo: Mar --> Arena --> Sol (Sol no tiene relacion directa con Arena por lo cual se podría discrepar).\n\",\"1\")," +
            "(null,\"Mesero\",\"El jugador girara una botella para elegir quien sera su mesero, el jugador que haya sido seleccionado debera ser su mesero hasta que esta carta vuelva a salir.\",\"1\")," +
            "(null,\"Marcianos\",\"Los jugadores se enumeraran empezando por quien saco la carta, y el mismo comenzara SIN MOSTRAR LOS DIENTES y CON LAS MANOS EN LA CABEZA llamando a sus compañeros por ejemplo: Marcianito 1 (ya que el empieza) llamando a Marcianito 3. Todos los participantes pueden hacer ruidos para hacer reir a los demas y quien muestre primero los dientes perdera.\n\",\"1\")," +
            "(null,\"Caricachupas\",\"Es el clasico juego de caricaturas. Al ritmo de los aplausos cada jugador debe de mencionar un objeto que sea valido dentro del tema elejido siguiendo la dinamica: Caricachupas --> Presenta --> Nombres De --> TEMA A ELEGIR --> Por Ejemplo --> PRIMER OBJETO... (El primero en equivocarse pierde el juego).\",\"1\")," +
            "(null,\"El Abuelo\",\"Primero se deben de enumerar los jugadores, y entre ellos se llamaran entre si usando la siguiente dinamica [Cuando el abuelo murio 4 copas de vino dejo --> (El numero 4 responde) ¿Y por que 4? --> (Vuelve a contestar el primero) ¿Entonces cuantas? --> 6 --> (Contesta el 6) ¿Y por que 6? --> ¿Entonces cuantas?...] El juego sigue hasta que alguien se equivoque.\n\",\"1\")," +
            "(null,\"Balazos\",\"El ultimo jugador en tirarse al suelo y gritar BALAZOS!!! BALAZOS!!! perdera el juego.\",\"0\")," +
            "(null,\"Te Vendo Un Pato\",\"Empezara el jugador que saco la carta siguiendo la siguiente dinamica: (Indica en que sentido va) [Te vendo un pato (Derecha) -->  ¿Un Que? (Izquierda) -->  Un Pato (Derecha)-->  Te vendo un Pato (Derecha) -->  ¿Un Que? (Izquierda) --> ¿Un Que? (Izquierda) --> Un Pato (Derecha)-->  Un Pato (Derecha)--> Te vendo un pato (Derecha)...] y asi sucesivamente.\n\",\"0\")," +
            "(null,\"Cacique\",\"El jugador que obtenga esta carta puede asignar el castigo que quiera a la persona que el desee.\",\"0\")," +
            "(null,\"Verdad o Reto\",\"El jugador que obtenga esta carta debe de hacer girar una botella y la persona que sea elegida, debera seleccionar si desea verdad o castigo.\",\"0\")," +
            "(null,\"Carta Soplada*\",\"*CARTA(S) NECESARIA*\nConsiste en pasarse una carta de boca en boca, solo soplandola.\",\"0\")";

    public static final String DEFAULT_INSERT_CARD_GAMES = "insert into CARD_GAMES values" +
            "(null,\"AsR\"," + R.drawable.card_a_0 + "," + R.drawable.card_a_1 + "," + R.drawable.card_a_2 + "," + R.drawable.card_a_3 + "," + R.drawable.card_a_4 +","+
            R.drawable.card_a_5 +","+ R.drawable.card_a_6 +","+ R.drawable.card_a_7 +","+ R.drawable.card_a_8 + "," + R.drawable.card_a_f1 + "," + R.drawable.card_a_f2 + "," +
            R.drawable.card_a_f3 + "," + R.drawable.card_a_f4 +","+ R.drawable.card_a_f5 +","+ R.drawable.card_a_f6 +",\"1\")," +
            "(null,\"2R\"," + R.drawable.card_2_0 + "," + R.drawable.card_2_1 + "," + R.drawable.card_2_2 + "," + R.drawable.card_2_3 + "," + R.drawable.card_2_4 +","+
            R.drawable.card_2_5 +","+ R.drawable.card_2_6 +","+ R.drawable.card_2_7 +","+ R.drawable.card_2_8  + "," + R.drawable.card_2_f1 + "," + R.drawable.card_2_f2 + "," +
            R.drawable.card_2_f3 + "," + R.drawable.card_2_f4 +","+ R.drawable.card_2_f5 +","+ R.drawable.card_2_f6 + ",\"2\")," +
            "(null,\"3R\"," + R.drawable.card_3_0 + "," + R.drawable.card_3_1 + "," + R.drawable.card_3_2 + "," + R.drawable.card_3_3 + "," + R.drawable.card_3_4 +","+
            R.drawable.card_3_5 +","+ R.drawable.card_3_6 +","+ R.drawable.card_3_7 +","+ R.drawable.card_3_8 + "," + R.drawable.card_3_f1 + "," + R.drawable.card_3_f2 + "," +
            R.drawable.card_3_f3 + "," + R.drawable.card_3_f4 +","+ R.drawable.card_3_f5 +","+ R.drawable.card_3_f6 + ",\"3\")," +
            "(null,\"4R\"," + R.drawable.card_4_0 + "," + R.drawable.card_4_1 + "," + R.drawable.card_4_2 + "," + R.drawable.card_4_3 + "," + R.drawable.card_4_4 +","+
            R.drawable.card_4_5 +","+ R.drawable.card_4_6 +","+ R.drawable.card_4_7 +","+ R.drawable.card_4_8 + "," + R.drawable.card_4_f1 + "," + R.drawable.card_4_f2 + "," +
            R.drawable.card_4_f3 + "," + R.drawable.card_4_f4 +","+ R.drawable.card_4_f5 +","+ R.drawable.card_4_f6 + ",\"4\")," +
            "(null,\"5R\"," + R.drawable.card_5_0 + "," + R.drawable.card_5_1 + "," + R.drawable.card_5_2 + "," + R.drawable.card_5_3 + "," + R.drawable.card_5_4 +","+
            R.drawable.card_5_5 +","+ R.drawable.card_5_6 +","+ R.drawable.card_5_7 +","+ R.drawable.card_5_8  + "," + R.drawable.card_5_f1 + "," + R.drawable.card_5_f2 + "," +
            R.drawable.card_5_f3 + "," + R.drawable.card_5_f4 +","+ R.drawable.card_5_f5 +","+ R.drawable.card_5_f6 + ",\"5\")," +
            "(null,\"6R\"," + R.drawable.card_6_0 + "," + R.drawable.card_6_1 + "," + R.drawable.card_6_2 + "," + R.drawable.card_6_3 + "," + R.drawable.card_6_4 +","+
            R.drawable.card_6_5 +","+ R.drawable.card_6_6 +","+ R.drawable.card_6_7 +","+ R.drawable.card_6_8 + "," + R.drawable.card_6_f1 + "," + R.drawable.card_6_f2 + "," +
            R.drawable.card_6_f3 + "," + R.drawable.card_6_f4 +","+ R.drawable.card_6_f5 +","+ R.drawable.card_6_f6 + ",\"6\")," +
            "(null,\"7R\"," + R.drawable.card_7_0 + "," + R.drawable.card_7_1 + "," + R.drawable.card_7_2 + "," + R.drawable.card_7_3 + "," + R.drawable.card_7_4 +","+
            R.drawable.card_7_5 +","+ R.drawable.card_7_6 +","+ R.drawable.card_7_7 +","+ R.drawable.card_7_8 + "," + R.drawable.card_7_f1 + "," + R.drawable.card_7_f2 + "," +
            R.drawable.card_7_f3 + "," + R.drawable.card_7_f4 +","+ R.drawable.card_7_f5 +","+ R.drawable.card_7_f6 + ",\"7\")," +
            "(null,\"8R\"," + R.drawable.card_8_0 + "," + R.drawable.card_8_1 + "," + R.drawable.card_8_2 + "," + R.drawable.card_8_3 + "," + R.drawable.card_8_4 +","+
            R.drawable.card_8_5 +","+ R.drawable.card_8_6 +","+ R.drawable.card_8_7 +","+ R.drawable.card_8_8 + "," + R.drawable.card_8_f1 + "," + R.drawable.card_8_f2 + "," +
            R.drawable.card_8_f3 + "," + R.drawable.card_8_f4 +","+ R.drawable.card_8_f5 +","+ R.drawable.card_8_f6 + ",\"8\")," +
            "(null,\"9R\"," + R.drawable.card_9_0 + "," + R.drawable.card_9_1 + "," + R.drawable.card_9_2 + "," + R.drawable.card_9_3 + "," + R.drawable.card_9_4 +","+
            R.drawable.card_9_5 +","+ R.drawable.card_9_6 +","+ R.drawable.card_9_7 +","+ R.drawable.card_9_8 + "," + R.drawable.card_9_f1 + "," + R.drawable.card_9_f2 + "," +
            R.drawable.card_9_f3 + "," + R.drawable.card_9_f4 +","+ R.drawable.card_9_f5 +","+ R.drawable.card_9_f6 + ",\"9\")," +
            "(null,\"10R\"," + R.drawable.card_10_0 + "," + R.drawable.card_10_1 + "," + R.drawable.card_10_2 + "," + R.drawable.card_10_3 + "," + R.drawable.card_10_4 +","+
            R.drawable.card_10_5 +","+ R.drawable.card_10_6 +","+ R.drawable.card_10_7 +","+ R.drawable.card_10_8 + "," + R.drawable.card_10_f1 + "," + R.drawable.card_10_f2 + "," +
            R.drawable.card_10_f3 + "," + R.drawable.card_10_f4 +","+ R.drawable.card_10_f5 +","+ R.drawable.card_10_f6 + ",\"10\")," +
            "(null,\"JR\"," + R.drawable.card_j_0 + "," + R.drawable.card_j_1 + "," + R.drawable.card_j_2 + "," + R.drawable.card_j_3 + "," + R.drawable.card_j_4 +","+
            R.drawable.card_j_5 +","+ R.drawable.card_j_6 +","+ R.drawable.card_j_7 +","+ R.drawable.card_j_8 + "," + R.drawable.card_j_f1 + "," + R.drawable.card_j_f2 + "," +
            R.drawable.card_j_f3 + "," + R.drawable.card_j_f4 +","+ R.drawable.card_j_f5 +","+ R.drawable.card_j_f6 + ",\"11\")," +
            "(null,\"QR\"," + R.drawable.card_q_0 + "," + R.drawable.card_q_1 + "," + R.drawable.card_q_2 + "," + R.drawable.card_q_3 + "," + R.drawable.card_q_4 +","+
            R.drawable.card_q_5 +","+ R.drawable.card_q_6 +","+ R.drawable.card_q_7 +","+ R.drawable.card_q_8 + "," + R.drawable.card_q_f1 + "," + R.drawable.card_q_f2 + "," +
            R.drawable.card_q_f3 + "," + R.drawable.card_q_f4 +","+ R.drawable.card_q_f5 +","+ R.drawable.card_q_f6 + ",\"12\")," +
            "(null,\"KR\"," + R.drawable.card_k_0 + "," + R.drawable.card_k_1 + "," + R.drawable.card_k_2 + "," + R.drawable.card_k_3 + "," + R.drawable.card_k_4 +","+
            R.drawable.card_k_5 +","+ R.drawable.card_k_6 +","+ R.drawable.card_k_7 +","+ R.drawable.card_k_8 + "," + R.drawable.card_k_f1 + "," + R.drawable.card_k_f2 + "," +
            R.drawable.card_k_f3 + "," + R.drawable.card_k_f4 +","+ R.drawable.card_k_f5 +","+ R.drawable.card_k_f6 + ",\"13\")";

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
