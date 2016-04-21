package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        final Button play = (Button) findViewById(R.id.menu_button_play);
        final Button games = (Button) findViewById(R.id.menu_button_games);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(ActivityMain.this, ActivityPlay.class);
                startActivity(playIntent);
            }
        });
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamesIntent = new Intent(ActivityMain.this, ActivityGameList.class);
                gamesIntent.putExtra("edit", false);
                gamesIntent.putExtra("gameOld", -1);
                startActivity(gamesIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);
        builder.setMessage("¿Estas seguro que deseas salir?")
                .setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton(R.string.detail_dialog_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
