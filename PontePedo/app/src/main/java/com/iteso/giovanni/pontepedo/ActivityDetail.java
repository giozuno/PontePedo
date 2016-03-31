package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        TextView title = (TextView) findViewById(R.id.detail_game_title);
        TextView description = (TextView) findViewById(R.id.detail_game_description);
        Button cambiarJuego = (Button) findViewById(R.id.detail_change_game);
        
        Bundle gameID = getIntent().getExtras();
        final int posGame = gameID.getInt("value");
//        title.setText(Games.getTitle(posGame));
        switch (posGame) {
            case 0:
                title.setText(getString(R.string.game_A));
                description.setText(getString(R.string.desc_A));
                break;
            case 1:
                title.setText(getString(R.string.game_2));
                description.setText(getString(R.string.desc_2));
                break;
            case 2:
                title.setText(getString(R.string.game_3));
                description.setText(getString(R.string.desc_3));
                break;
            case 3:
                title.setText(getString(R.string.game_4));
                description.setText(getString(R.string.desc_4));
                break;
            case 4:
                title.setText(getString(R.string.game_5));
                description.setText(getString(R.string.desc_5));
                break;
            case 5:
                title.setText(getString(R.string.game_6));
                description.setText(getString(R.string.desc_6));
                break;
            case 6:
                title.setText(getString(R.string.game_7));
                description.setText(getString(R.string.desc_7));
                break;
            case 7:
                title.setText(getString(R.string.game_8));
                description.setText(getString(R.string.desc_8));
                break;
            case 8:
                title.setText(getString(R.string.game_9));
                description.setText(getString(R.string.desc_9));
                break;
            case 9:
                title.setText(getString(R.string.game_10));
                description.setText(getString(R.string.desc_10));
                break;
            case 10:
                title.setText(getString(R.string.game_J));
                description.setText(getString(R.string.desc_J));
                break;
            case 11:
                title.setText(getString(R.string.game_Q));
                description.setText(getString(R.string.desc_Q));
                break;
            case 12:
                title.setText(getString(R.string.game_K));
                description.setText(getString(R.string.desc_K));
                break;
        }

        cambiarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDetail.this);
                builder.setMessage(R.string.detail_dialog_message).
                        setPositiveButton(R.string.detail_dialog_possitive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent changeIntent = new Intent(ActivityDetail.this, ActivityChangeGame.class);
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