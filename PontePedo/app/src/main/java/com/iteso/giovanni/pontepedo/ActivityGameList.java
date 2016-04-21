package com.iteso.giovanni.pontepedo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class ActivityGameList extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        final ListView listGames = (ListView) findViewById(R.id.listGames);
        ImageButton createGame = (ImageButton) findViewById(R.id.create_new_game_button);
        ImageButton editList = (ImageButton) findViewById(R.id.edit_list_game_button);
        Bundle extras = getIntent().getExtras();
        final boolean edit = extras.getBoolean("edit");
        final int gameOld = extras.getInt("gameOld");

        AdapterGame adapterGame = null;
        if(edit) {
            adapterGame = new AdapterGame(this, dbOperations.getGamesNotPlayingCursor(), false);
        }
        else
            adapterGame = new AdapterGame(this, dbOperations.getGamesPlayingCursor(), false);

        listGames.setAdapter(adapterGame);
        listGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityGameList.this, ActivityGameDetail.class);
                Cursor c = (Cursor) listGames.getItemAtPosition(position);
                intent.putExtra("pos", c.getInt(0));
                if(edit)
                    intent.putExtra("gameOld", gameOld);
                intent.putExtra("edit", true);
                intent.putExtra("change", edit);
                startActivity(intent);
            }
        });
        createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGameList.this, ActivityCreateGame.class);
                startActivity(intent);
            }
        });
        editList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGameList.this, ActivityEditGameList.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityGameList.this, ActivityMain.class);
        startActivity(intent);
    }
}
