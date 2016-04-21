package com.iteso.giovanni.pontepedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class ActivityEditGameList extends AppCompatActivity {
    ListView gameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        Button continueButton = (Button) findViewById(R.id.edit_game_list_continue);
        gameList = (ListView) findViewById(R.id.edit_game_listView);
        final AdapterEditGame adapterEditGame = new AdapterEditGame(this, dbOperations.getGamesCursor(), false);
        gameList.setAdapter(adapterEditGame);

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView game = (CheckedTextView) view;
                if(game.isChecked())
                    gameList.setItemChecked(position, true);
                else
                    gameList.setItemChecked(position, false);
            }
        });

//        gameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEditGameList.this);
//                builder.setMessage("¿Deseas ver la informacion del juego?")
//                        .setPositiveButton(R.string.detail_dialog_possitive_button, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(ActivityEditGameList.this, ActivityGameDetail.class);
//                                intent.putExtra("pos", position + 1);
//                                startActivity(intent);
//                            }
//                        }).setNegativeButton(R.string.detail_dialog_negative_button, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // No hacer nada
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//                return false;
//            }
//        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityEditGameList.this, ActivityGameDetail.class);
                intent.putExtra("pos", gameList.getCheckedItemCount());
                startActivity(intent);
//                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEditGameList.this);
//                builder.setMessage(R.string.detail_dialog_message).
//                        setPositiveButton(R.string.detail_dialog_possitive_button, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Continuar solo si el numero de juegos seleccionados es el correcto
//                                int nCheck = 0;
//                                for(int i = 0; i < gameList.getCount(); i++){
//                                        nCheck++;
//                                }
//                                if(nCheck == 13) {
//                                    Intent intent = new Intent(ActivityEditGameList.this, ActivityGameList.class);
//                                    startActivity(intent);
//                                }
//                            }
//                        }).
//                        setNegativeButton(R.string.detail_dialog_negative_button, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // No hace nada
//                            }
//                        });
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });
    }
}