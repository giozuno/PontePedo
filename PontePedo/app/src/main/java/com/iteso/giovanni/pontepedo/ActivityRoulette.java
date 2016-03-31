package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityRoulette extends AppCompatActivity {
    String reto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final ImageButton roulette = (ImageButton) findViewById(R.id.roulette);
        final TextView tileRoulette = (TextView) findViewById(R.id.title_roulette);
        Button button = (Button) findViewById(R.id.buttonRoulette);
        roulette.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int numRnd = (int)(rnd.nextDouble() * 16 + 8);
                int x=0, i;
                for(i=0; i<numRnd; i++) {
                    delay(1);
                    x = i;
                    while(x>7)
                        x -= 8;
                    switch (x) {
                        case 0:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta1));
                            reto = getString(R.string.reto1);
                            break;
                        case 1:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta2));
                            reto = getString(R.string.reto2);
                            break;
                        case 2:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta3));
                            reto = getString(R.string.reto3);
                            break;
                        case 3:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta4));
                            reto = getString(R.string.reto4);
                            break;
                        case 4:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta5));
                            reto = getString(R.string.reto5);
                            break;
                        case 5:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta6));
                            reto = getString(R.string.reto6);
                            break;
                        case 6:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta7));
                            reto = getString(R.string.reto7);
                            break;
                        case 7:
                            roulette.setImageDrawable(getDrawable(R.drawable.ruleta8));
                            reto = getString(R.string.reto8);
                            break;
                    }
                }
                tileRoulette.setText(reto);
                Toast.makeText(getApplicationContext(), "i: " + i + "   x: " + x + "   rnd: " + numRnd, Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRoulette.this, ActivityPlay.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_roulette, menu);
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

    public void delay(int sec){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, sec * 1000);
    }
}
