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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final EditText newTitle = (EditText) findViewById(R.id.change_text_title);
        final EditText newDescription = (EditText) findViewById(R.id.change_text_description);
        Button confirm = (Button) findViewById(R.id.change_button_confirm);
        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        final Boolean deleteEnable = extras.getBoolean(getString(R.string.intent_delete));
        final Boolean editEnable = extras.getBoolean(getString(R.string.intent_edit));
        final Boolean changeEnable = extras.getBoolean(getString(R.string.intent_change));
        
        final int gameOld = extras.getInt(getString(R.string.intent_gameOld), -1);

        if(deleteEnable || changeEnable)
            Toast.makeText(getApplicationContext(), R.string.create_game_message_instructions, Toast.LENGTH_LONG).show();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newTitle.getText().toString().isEmpty() && !newDescription.getText().toString().isEmpty()) {
                    dbOperations.addGame(newTitle.getText().toString(), newDescription.getText().toString());
                    final int idNewGame = dbOperations.getGame(newTitle.getText().toString()).getId();
                    if(!changeEnable && !editEnable) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityCreateGame.this);
                        builder.setMessage("¿Deseas agregar este juego a tu lista de juegos actuales?").
                                setPositiveButton(R.string.dialog_yes_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
                                        intent.putExtra(getString(R.string.intent_gameNew), idNewGame);
                                        intent.putExtra(getString(R.string.intent_create), true);
                                        startActivity(intent);
                                    }
                                }).
                                setNegativeButton(R.string.dialog_no_button, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
                                        // Mostrar lista de no jugados
                                        startActivity(intent);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    if(changeEnable) {
                        dbOperations.changeCardGame(gameOld, idNewGame);
                        Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
                        intent.putExtra(getString(R.string.intent_edit), editEnable);
                        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
                        startActivity(intent);
                    }
                    else if(deleteEnable) {
                        dbOperations.changeCardGame(gameOld, idNewGame);
                        dbOperations.deleteGame(gameOld);
                        Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
                        intent.putExtra(getString(R.string.intent_edit), editEnable);
                        intent.putExtra(getString(R.string.intent_gameOld), gameOld);
                        startActivity(intent);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), R.string.create_game_message_error_empty, Toast.LENGTH_LONG).show();
            }
        });

    }
}
