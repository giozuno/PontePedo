package com.iteso.giovanni.pontepedo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        Bundle gameID = getIntent().getExtras();
        final int posGame = gameID.getInt("pos");

        Game chooseGame = dbOperations.getGame(posGame + 1);
        title.setText(chooseGame.getName());
        description.setText(chooseGame.getDescription());
    }
}