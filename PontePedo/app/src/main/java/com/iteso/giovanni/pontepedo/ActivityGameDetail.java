package com.iteso.giovanni.pontepedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityGameDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());
        TextView title = (TextView) findViewById(R.id.detail_game_title);
        TextView description = (TextView) findViewById(R.id.detail_game_description);
        Button edit = (Button) findViewById(R.id.detail_game_edit);

        final Bundle gameID = getIntent().getExtras();
        final int posGame = gameID.getInt(getString(R.string.intent_pos), -1);
        final int gameOld = gameID.getInt(getString(R.string.intent_gameOld), -1);
        final int gameNew = gameID.getInt(getString(R.string.intent_gameNew), -1);
        final boolean editPlayingEnable = gameID.getBoolean(getString(R.string.intent_edit_playing));
        final boolean changeEnable = gameID.getBoolean(getString(R.string.intent_change));
        final boolean editNotPlayingEnable = gameID.getBoolean(getString(R.string.intent_edit_notPlaying));
        final boolean playingEnable = gameID.getBoolean(getString(R.string.intent_playing));
        final boolean createEnable = gameID.getBoolean(getString(R.string.intent_create));
        final boolean onlyInfo = gameID.getBoolean(getString(R.string.intent_onlyInfo));

        if(changeEnable)
            edit.setText(R.string.game_detail_chooseGame);
        else if(onlyInfo) // Si se consulta cuando el usuario esta jugando
            edit.setVisibility(View.INVISIBLE);
        else if(!playingEnable)
            edit.setText(R.string.gameDetail_buttonText_AñadirJuego);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNotPlayingEnable && createEnable) { // Cuando se cambia el juego creado por uno viejo
                    dbOperations.changeCardGame(posGame, gameNew);
                    makeIntentToList(-1, -1, false, false, false);
                }
                else if (editPlayingEnable && changeEnable) { // Cuando se cambia un juego viejo por uno nuevo seleccionado
                    dbOperations.changeCardGame(gameOld, posGame);
                    makeIntentToList(-1, -1, false, false, false);
                }
                else if (!editPlayingEnable && changeEnable) { // Cuando se elimina un juego y se remplaza por otro
                    dbOperations.changeCardGame(gameOld, posGame);
                    dbOperations.deleteGame(gameOld);
                    makeIntentToList(-1, -1, false, false, false);
                }
                else {
                    if (playingEnable)
                        makeIntentToList(posGame, -1, true, false, false);
                    else
                        makeIntentToList(-1, posGame, false, true, true);
                }
            }
        });
        Game chooseGame = dbOperations.getGame(posGame);
        title.setText(chooseGame.getName());
        description.setText(chooseGame.getDescription());
    }

    private void makeIntentToList(int gameOld, int gameNew, boolean editPlaying, boolean editNotPlaying, boolean create) {
        Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
        intent.putExtra(getString(R.string.intent_gameNew), gameNew);
        intent.putExtra(getString(R.string.intent_edit_playing), editPlaying);
        intent.putExtra(getString(R.string.intent_edit_notPlaying), editNotPlaying);
        intent.putExtra(getString(R.string.intent_create), create);
        startActivity(intent);
    }
}