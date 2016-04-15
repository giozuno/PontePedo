package com.iteso.giovanni.pontepedo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityEditGameList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        Button continueButton = (Button) findViewById(R.id.edit_game_list_continue);
        final ListView listGames = (ListView) findViewById(R.id.edit_game_listView);
        final AdapterEditGame adapterEditGame = new AdapterEditGame(this, dbOperations.getGamesCursor(), false);
        listGames.setAdapter(adapterEditGame);

        // Marcar como seleccionados los juegos que su checked es true;

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEditGameList.this);
                builder.setMessage(R.string.detail_dialog_message).
                        setPositiveButton(R.string.detail_dialog_possitive_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Continuar solo si el numero de juegos seleccionados es el correcto
                                Toast.makeText(getApplicationContext(), "DEBERIA GUARDAR TU ELECCION", Toast.LENGTH_SHORT).show();
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
