package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCreateGame extends AppCompatActivity {
    int idNewGame;
    DataBaseOperations dbOperations;
    EditText newTitle, newDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        newTitle = (EditText) findViewById(R.id.change_text_title);
        newDescription = (EditText) findViewById(R.id.change_text_description);
        Button confirm = (Button) findViewById(R.id.change_button_confirm);

        dbOperations = new DataBaseOperations(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        final Boolean deleteEnable = extras.getBoolean(getString(R.string.intent_delete));
        final Boolean editPlayingEnable = extras.getBoolean(getString(R.string.intent_edit_playing));
        final Boolean changeEnable = extras.getBoolean(getString(R.string.intent_change));
        final int gameOld = extras.getInt(getString(R.string.intent_gameOld), -1);

        if(deleteEnable || changeEnable)
            Toast.makeText(getApplicationContext(), R.string.create_game_message_instructions, Toast.LENGTH_LONG).show();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newTitle.getText().toString().isEmpty() && !newDescription.getText().toString().isEmpty()) {
                    if(!changeEnable && !deleteEnable) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityCreateGame.this);
                        builder.setMessage(R.string.create_dialog_add_actGame).
                                setPositiveButton(R.string.dialog_yes_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        addGame();
                                        makeIntentToList(-1, idNewGame, false, true, true, false);
                                    }
                                }).
                                setNegativeButton(R.string.dialog_no_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        addGame();
                                        makeIntentToList(-1, -1, false, false, false, true);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else if(changeEnable) {
                        addGame();
                        dbOperations.changeCardGame(gameOld, idNewGame);
                        makeIntentToList(gameOld, -1, editPlayingEnable, false, false, false);
                    }
                    else{
                        addGame();
                        dbOperations.changeCardGame(gameOld, idNewGame);
                        dbOperations.deleteGame(gameOld);
                        makeIntentToList(-1, -1, false, false, false, false);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), R.string.create_game_message_error_empty, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addGame() {
        dbOperations.addGame(newTitle.getText().toString(), newDescription.getText().toString());
        this.idNewGame = dbOperations.getGame(newTitle.getText().toString()).getId();
    }
    
    private void makeIntentToList(int gameOld, int gameNew, boolean editPlaying, boolean editNotPlaying, boolean create, boolean viewNotPlaying) {
        Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
        intent.putExtra(getString(R.string.intent_gameNew), gameNew);
        intent.putExtra(getString(R.string.intent_edit_playing), editPlaying);
        intent.putExtra(getString(R.string.intent_edit_notPlaying), editNotPlaying);
        intent.putExtra(getString(R.string.intent_create), create);
        intent.putExtra(getString(R.string.intent_view_NotPlaying), viewNotPlaying);
        startActivity(intent);
    }
}
