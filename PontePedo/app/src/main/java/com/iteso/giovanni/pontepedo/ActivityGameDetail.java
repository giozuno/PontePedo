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
        final int posGame = gameID.getInt("pos");
        final boolean editEnable = gameID.getBoolean("edit");
        final boolean changeEnable = gameID.getBoolean("change");

        if(changeEnable)
            edit.setText("Elegir Juego");
        if(!editEnable) {
            edit.setPressed(true);
            edit.setClickable(false);
        }
        else if(editEnable && changeEnable)
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameOld = gameID.getInt("gameOld");
                    dbOperations.changeGame(gameOld, posGame);
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra("edit", false);
                    intent.putExtra("gameOld", -1);
                    startActivity(intent);
                }
            });
        else{
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityGameDetail.this, ActivityGameList.class);
                    intent.putExtra("edit", true);
                    intent.putExtra("gameOld", posGame);
                    startActivity(intent);
                }
            });
        }
        Game chooseGame = dbOperations.getGame(posGame);
        title.setText(chooseGame.getName());
        description.setText(chooseGame.getDescription());

        // PRUEBA PARA LISTA DE EDIT GAME
//        title.setText(posGame);
//        description.setText(posGame);
    }
}