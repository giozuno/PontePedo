package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityGameList extends AppCompatActivity{
    boolean editList = false;
    boolean deleteEnable = false;
    int gameNew = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        final ListView listGames = (ListView) findViewById(R.id.listGames);
        ImageButton createGame = (ImageButton) findViewById(R.id.create_new_game_button);
        Bundle extras = getIntent().getExtras();
        final boolean editEnable = extras.getBoolean(getString(R.string.intent_edit), false);
        final boolean createEnable = extras.getBoolean(getString(R.string.intent_create));
        final int gameOld = extras.getInt(getString(R.string.intent_gameOld), -1);

        AdapterGame adapterGame = null;
        if(editEnable) {
            Toast.makeText(getApplicationContext(), R.string.select_game_replace_deleted, Toast.LENGTH_LONG).show();
            adapterGame = new AdapterGame(this, dbOperations.getGamesNotPlayingCursor(), false);
            editList = true;
            deleteEnable = extras.getBoolean(getString(R.string.intent_delete));
//            listGames.setLongClickable(false);
        }
        else {
            adapterGame = new AdapterGame(this, dbOperations.getGamesPlayingCursor(), false);
            if (createEnable) {
                Toast.makeText(getApplicationContext(), "Selecciona el juego a remplazar", Toast.LENGTH_SHORT).show();
                createGame.setVisibility(View.INVISIBLE);
                gameNew = extras.getInt(getString(R.string.intent_gameNew));
                listGames.setLongClickable(false);
            }
        }

        listGames.setAdapter(adapterGame);
        listGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityGameList.this, ActivityGameDetail.class);
                Cursor c = (Cursor) listGames.getItemAtPosition(position);
                intent.putExtra(getString(R.string.intent_pos), c.getInt(0));
                if (editEnable)
                    intent.putExtra(getString(R.string.intent_gameOld), gameOld);
                if (deleteEnable)
                    intent.putExtra(getString(R.string.intent_edit), false);
                else
                    intent.putExtra(getString(R.string.intent_edit), true);
                intent.putExtra(getString(R.string.intent_change), editEnable);
                intent.putExtra(getString(R.string.intent_create), createEnable);
                intent.putExtra(getString(R.string.intent_gameNew), gameNew);
                startActivity(intent);
            }
        });

        listGames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int positionLongClick, long id) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ActivityGameList.this);
                final ArrayAdapter<String> options = new ArrayAdapter<String>(ActivityGameList.this,
                        android.R.layout.select_dialog_item);
                options.add(getString(R.string.options_dialog_seeGame));
                options.add(getString(R.string.options_dialog_deleteGame));
                options.add(getString(R.string.options_dialog_changeGame));

                builderSingle.setAdapter(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (options.getItem(which) == getString(R.string.options_dialog_seeGame)) {
                            Intent intent = new Intent(ActivityGameList.this, ActivityGameDetail.class);
                            Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                            intent.putExtra(getString(R.string.intent_pos), c.getInt(0));
                            if (editEnable)
                                intent.putExtra(getString(R.string.intent_gameOld), gameOld);
                            intent.putExtra(getString(R.string.intent_edit), true);
                            intent.putExtra(getString(R.string.intent_change), editEnable);
                            startActivity(intent);

                        } else if (options.getItem(which) == getString(R.string.options_dialog_deleteGame)) {
                            if (new DataBaseOperations(getApplicationContext()).getGamesNotPlayingCursor().getCount() != 0) {
                                Intent intent = new Intent(ActivityGameList.this, ActivityGameList.class);
                                Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                                intent.putExtra(getString(R.string.intent_gameOld), c.getInt(0));
                                intent.putExtra(getString(R.string.intent_delete), true);
                                intent.putExtra(getString(R.string.intent_edit), true);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(ActivityGameList.this, ActivityCreateGame.class);
                                Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                                intent.putExtra(getString(R.string.intent_gameOld), c.getInt(0));
                                intent.putExtra(getString(R.string.intent_delete), true);
                                intent.putExtra(getString(R.string.intent_edit), editEnable);
                                startActivity(intent);
                            }

                        } else {
                            if (new DataBaseOperations(getApplicationContext()).getGamesNotPlayingCursor().getCount() != 0) {
                                Intent intent = new Intent(ActivityGameList.this, ActivityGameList.class);
                                intent.putExtra(getString(R.string.intent_edit), true);
                                intent.putExtra(getString(R.string.intent_delete), false);
                                Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                                intent.putExtra(getString(R.string.intent_gameOld), c.getInt(0));
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(ActivityGameList.this, ActivityCreateGame.class);
                                Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                                intent.putExtra(getString(R.string.intent_gameOld), c.getInt(0));
                                intent.putExtra(getString(R.string.intent_change), true);
                                intent.putExtra(getString(R.string.intent_edit), editEnable);
                                startActivity(intent);
                            }
                        }
                    }
                });
                Dialog dialog = builderSingle.create();
                dialog.show();
                return true;
            }
        });
        createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityGameList.this, ActivityCreateGame.class);
                intent.putExtra(getString(R.string.intent_delete), false);
                intent.putExtra(getString(R.string.intent_edit), editEnable);
                intent.putExtra(getString(R.string.intent_gameOld), gameOld);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(editList)
            super.onBackPressed();
        else {
            Intent intent = new Intent(ActivityGameList.this, ActivityMain.class);
            startActivity(intent);
        }
    }
}
