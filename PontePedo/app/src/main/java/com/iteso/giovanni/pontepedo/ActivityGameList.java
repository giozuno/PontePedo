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
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityGameList extends AppCompatActivity {
    private AdapterGame adapterGame = null;
    private DataBaseOperations dbOperations = null;
    private Spinner listChoice;
    private ImageButton createGame;
    private ListView listGames;
    private int gameOld, gameNew;
    private boolean editPlayingEnable, editNotPlayingEnable, deleteEnable, createEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        dbOperations = new DataBaseOperations(getApplicationContext());

        listGames = (ListView) findViewById(R.id.listGames);
        createGame = (ImageButton) findViewById(R.id.create_new_game_button);
        listChoice = (Spinner) findViewById(R.id.spinner_list_choice);

        Bundle extras = getIntent().getExtras();
        gameOld = extras.getInt(getString(R.string.intent_gameOld), -1);
        gameNew = extras.getInt(getString(R.string.intent_gameNew), -1);
        editPlayingEnable = extras.getBoolean(getString(R.string.intent_edit_playing));
        editNotPlayingEnable = extras.getBoolean(getString(R.string.intent_edit_notPlaying));
        deleteEnable = extras.getBoolean(getString(R.string.intent_delete));
        createEnable = extras.getBoolean(getString(R.string.intent_create));
        boolean viewNotPlaying = extras.getBoolean(getString(R.string.intent_view_NotPlaying));

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(ActivityGameList.this, R.layout.item_spinner);
        adapterSpinner.add(getString(R.string.options_spinner_selectedGames));
        adapterSpinner.add(getString(R.string.options_spinner_notSelectedGames));
        adapterSpinner.setDropDownViewResource(R.layout.item_spinner_drop_down);
        listChoice.setAdapter(adapterSpinner);

//        if(deleteEnable && editPlayingEnable)
//            createGame.setVisibility(View.INVISIBLE);
        if(viewNotPlaying)
            listChoice.setSelection(1);
        if(editNotPlayingEnable) {
            Toast.makeText(getApplicationContext(), R.string.select_game_to_replace, Toast.LENGTH_SHORT).show();
            adapterGame = new AdapterGame(this, dbOperations.getGamesPlayingCursor(), false);
            listGames.setLongClickable(false);
            listChoice.setSelection(0);
            listChoice.setEnabled(false);
            setList(true);
        }
        else if(editPlayingEnable) {
            Toast.makeText(getApplicationContext(), R.string.select_game_replace_deleted, Toast.LENGTH_LONG).show();
            adapterGame = new AdapterGame(this, dbOperations.getGamesNotPlayingCursor(), false);
            listGames.setLongClickable(false);
            listChoice.setSelection(1);
            listChoice.setEnabled(false);
            setList(false);
        }
        else {
            listChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(listChoice.getItemAtPosition(position).equals(getString(R.string.options_spinner_selectedGames))) {
                        adapterGame = new AdapterGame(ActivityGameList.this, dbOperations.getGamesPlayingCursor(), false);
                        setList(true);
                    }
                    else if(listChoice.getItemAtPosition(position).equals(getString(R.string.options_spinner_notSelectedGames))) {
                        adapterGame = new AdapterGame(ActivityGameList.this, dbOperations.getGamesNotPlayingCursor(), false);
                        setList(false);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing
                }
            });
        }
    }

    private void setList(final boolean isGamePlaying) {
        listGames.setAdapter(adapterGame);
        listGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) listGames.getItemAtPosition(position);
                makeIntentToDetail(c.getInt(0), gameOld, gameNew, !deleteEnable, editPlayingEnable, editNotPlayingEnable, isGamePlaying,
                        createEnable);
            }
        });

        listGames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int positionLongClick, long id) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(ActivityGameList.this);
                final ArrayAdapter<String> options = new ArrayAdapter<>(ActivityGameList.this,
                        android.R.layout.select_dialog_item);
                options.add(getString(R.string.options_dialog_seeGame));
                options.add(getString(R.string.options_dialog_deleteGame));
                options.add(getString(R.string.options_dialog_changeGame));

                builderSingle.setAdapter(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Cursor c = (Cursor) listGames.getItemAtPosition(positionLongClick);
                        if (options.getItem(which).equals(getString(R.string.options_dialog_seeGame)))
                            makeIntentToDetail(c.getInt(0), gameOld, gameNew, !deleteEnable, editPlayingEnable, editNotPlayingEnable, isGamePlaying,
                                    createEnable);
                        else if (options.getItem(which).equals(getString(R.string.options_dialog_deleteGame))) {
                            if (!isGamePlaying) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGameList.this);
                                builder.setMessage(R.string.gameList_dialog_deleteGame).
                                        setPositiveButton(R.string.dialog_yes_button, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dbOperations.deleteGame(c.getInt(0));
                                                makeIntentToList(-1, -1, false, false, false, false, true);
                                            }
                                        }).
                                        setNegativeButton(R.string.dialog_no_button, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // do nothing
                                            }
                                });
                                Dialog dialog1 = builder.create();
                                dialog1.show();
                            }
                            else if (new DataBaseOperations(getApplicationContext()).getGamesNotPlayingCursor().getCount() != 0)
                                makeIntentToList(c.getInt(0), -1, true, false, true, false, false);
                            else
                                makeIntentToCreate(c.getInt(0), false, false, true, false);
                        }

                        else if (options.getItem(which).equals(getString(R.string.options_dialog_changeGame))) {
                            if (!isGamePlaying)
                                makeIntentToList(-1, c.getInt(0), false, true, false, true, false);
                            else if (new DataBaseOperations(getApplicationContext()).getGamesNotPlayingCursor().getCount() != 0)
                                makeIntentToList(c.getInt(0), -1, true, false, false, false, false);
                            else
                                makeIntentToCreate(c.getInt(0), false, false, false, true);
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
                makeIntentToCreate(gameOld, editPlayingEnable, false, deleteEnable, false);
            }
        });
    }

    private void makeIntentToDetail(int pos, int gameOld, int gameNew, boolean editPlaying, boolean change, boolean editNotPlaying,
                                    boolean playing, boolean create) {
        Intent intent = new Intent(ActivityGameList.this, ActivityGameDetail.class);
        intent.putExtra(getString(R.string.intent_pos), pos);
        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
        intent.putExtra(getString(R.string.intent_gameNew), gameNew);
        intent.putExtra(getString(R.string.intent_edit_playing), editPlaying);
        intent.putExtra(getString(R.string.intent_change), change);
        intent.putExtra(getString(R.string.intent_edit_notPlaying), editNotPlaying);
        intent.putExtra(getString(R.string.intent_playing), playing);
        intent.putExtra(getString(R.string.intent_create), create);
        startActivity(intent);
    }

    private void makeIntentToList(int gameOld, int gameNew, boolean editPlaying, boolean editNotPlaying, boolean delete, boolean create,
                                  boolean viewNotPlaying) {
        Intent intent = new Intent(ActivityGameList.this, ActivityGameList.class);
        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
        intent.putExtra(getString(R.string.intent_gameNew), gameNew);
        intent.putExtra(getString(R.string.intent_edit_playing), editPlaying);
        intent.putExtra(getString(R.string.intent_edit_notPlaying), editNotPlaying);
        intent.putExtra(getString(R.string.intent_delete), delete);
        intent.putExtra(getString(R.string.intent_create), create);
        intent.putExtra(getString(R.string.intent_view_NotPlaying), viewNotPlaying);
        startActivity(intent);
    }

    private void makeIntentToCreate(int gameOld, boolean editPlaying, boolean editNotPlaying, boolean delete, boolean change) {
        Intent intent = new Intent(ActivityGameList.this, ActivityCreateGame.class);
        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
        intent.putExtra(getString(R.string.intent_edit_playing), editPlaying);
        intent.putExtra(getString(R.string.intent_edit_notPlaying), editNotPlaying);
        intent.putExtra(getString(R.string.intent_delete), delete);
        intent.putExtra(getString(R.string.intent_change), change);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(editPlayingEnable || editNotPlayingEnable)
            super.onBackPressed();
        else {
            Intent intent = new Intent(ActivityGameList.this, ActivityMain.class);
            startActivity(intent);
        }
    }
}
