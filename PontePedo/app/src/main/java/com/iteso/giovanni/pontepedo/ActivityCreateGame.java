package com.iteso.giovanni.pontepedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newTitle.getText().toString().isEmpty() && !newDescription.getText().toString().isEmpty()) {
                    dbOperations.addGame(newTitle.getText().toString(), newDescription.getText().toString());
                    Intent intent = new Intent(ActivityCreateGame.this, ActivityGameList.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Se debe de introducir un Titulo y una Descripcion " +
                            "al nuevo juego para seguir", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_change_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
