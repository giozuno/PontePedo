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
        actionBar.hide();

        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());
        TextView title = (TextView) findViewById(R.id.detail_game_title);
        TextView description = (TextView) findViewById(R.id.detail_game_description);
        Button edit = (Button) findViewById(R.id.detail_game_edit);

        final Bundle gameID = getIntent().getExtras();
        final int posGame = gameID.getInt(getString(R.string.intent_pos));
        final boolean editEnable = gameID.getBoolean(getString(R.string.intent_edit));
        final boolean changeEnable = gameID.getBoolean(getString(R.string.intent_change));
        final boolean createEnable = gameID.getBoolean(getString(R.string.intent_create));

        if(changeEnable)
            edit.setText(R.string.game_detail_chooseGame);
        if(!editEnable && !changeEnable) {
            edit.setPressed(true);
            edit.setClickable(false);
        }
        else if(createEnable) {
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameNew = gameID.getInt(getString(R.string.intent_gameNew));
                    dbOperations.changeCardGame(posGame, gameNew);
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra(getString(R.string.intent_edit), false);
                    intent.putExtra(getString(R.string.intent_gameOld), -1);
                    intent.putExtra(getString(R.string.intent_delete), false);
                    startActivity(intent);
                }
            });
        }
        else if(editEnable && changeEnable)
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameOld = gameID.getInt(getString(R.string.intent_gameOld));
                    dbOperations.changeCardGame(gameOld, posGame);
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra(getString(R.string.intent_edit), false);
                    intent.putExtra(getString(R.string.intent_gameOld), -1);
                    intent.putExtra(getString(R.string.intent_delete), false);
                    startActivity(intent);
                }
            });
        else if (!editEnable && changeEnable) {
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameOld = gameID.getInt(getString(R.string.intent_gameOld));
                    dbOperations.changeCardGame(gameOld, posGame);
                    dbOperations.deleteGame(gameOld);
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra(getString(R.string.intent_edit), false);
                    intent.putExtra(getString(R.string.intent_gameOld), -1);
                    intent.putExtra(getString(R.string.intent_delete), false);
                    startActivity(intent);
                }
            });
        }
        else{
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra(getString(R.string.intent_edit), true);
                    intent.putExtra(getString(R.string.intent_gameOld), posGame);
                    intent.putExtra(getString(R.string.intent_delete), false);
                    startActivity(intent);
                }
            });
        }
        Game chooseGame = dbOperations.getGame(posGame);
        title.setText(chooseGame.getName());
        description.setText(chooseGame.getDescription());
    }
}