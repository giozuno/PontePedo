package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        Button cambiarJuego = (Button) findViewById(R.id.detail_change_game);
        
        Bundle gameID = getIntent().getExtras();
        final int posGame = gameID.getInt("pos");

        Game chooseGame = dbOperations.getGame(posGame + 1);
        title.setText(chooseGame.getName());
        description.setText(chooseGame.getDescription());

        cambiarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGameDetail.this);
                builder.setMessage(R.string.detail_dialog_message).
                        setPositiveButton(R.string.detail_dialog_possitive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent changeIntent = new Intent(ActivityGameDetail.this, ActivityCreateGame.class);
                                changeIntent.putExtra("value", posGame);
                                startActivity(changeIntent);
                            }
                        }).
                        setNegativeButton(R.string.detail_dialog_negative_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // No hace nada
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}