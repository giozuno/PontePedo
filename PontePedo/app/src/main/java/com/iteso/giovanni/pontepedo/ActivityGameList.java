package com.iteso.giovanni.pontepedo;

import android.content.Intent;
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

        ListView listGames = (ListView) findViewById(R.id.listGames);
        ImageButton createGame = (ImageButton) findViewById(R.id.create_new_game_button);
        ImageButton editList = (ImageButton) findViewById(R.id.edit_list_game_button);

        AdapterGame adapterGame = new AdapterGame(this, dbOperations.getGamesCursor(), false);
        listGames.setAdapter(adapterGame);
        listGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityGameList.this, ActivityGameDetail.class);
                intent.putExtra("pos", position);
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
}
