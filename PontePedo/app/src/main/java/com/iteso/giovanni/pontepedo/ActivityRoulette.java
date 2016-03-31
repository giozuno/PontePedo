package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ActivityRoulette extends AppCompatActivity {
    String challenge;
    AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final TextView tileRoulette = (TextView) findViewById(R.id.title_roulette);
        Button button = (Button) findViewById(R.id.buttonRoulette);
        final ImageButton roulette = (ImageButton) findViewById(R.id.roulette);
        roulette.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int time = 300;
                animation = new AnimationDrawable();
                fullRoulette(time);
                Random rnd = new Random();
                int numRnd = (int)(rnd.nextDouble() * 7);
                switch (numRnd) {
                    case 0:
                        roulette1(time);
                        break;
                    case 1:
                        roulette2(time);
                        break;
                    case 2:
                        roulette3(time);
                        break;
                    case 3:
                        roulette4(time);
                    case 4:
                        roulette5(time);
                        break;
                    case 5:
                        roulette6(time);
                        break;
                    case 6:
                        roulette7(time);
                        break;
                    case 7:
                        roulette8(time);
                        break;
                }
                roulette.setImageDrawable(animation);
                animation.start();
                roulette.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tileRoulette.setText(challenge);
                        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ding_ganador);
                        mp.start();
                    }
                });
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fullRoulette(int sec) {
        this.animation.setOneShot(true);
        this.animation.addFrame(getDrawable(R.drawable.ruleta1), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta2), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta3), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta4), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta5), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta6), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta7), sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta8), sec);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette1(int sec){
        this.animation.addFrame(getDrawable(R.drawable.ruleta1), sec);
        challenge = getString(R.string.reto1);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette2(int sec){
        roulette1(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta2), sec);
        challenge = getString(R.string.reto2);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette3(int sec){
        roulette2(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta3), sec);
        challenge = getString(R.string.reto3);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette4(int sec){
        roulette3(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta4), sec);
        challenge = getString(R.string.reto4);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette5(int sec){
        roulette4(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta5), sec);
        challenge = getString(R.string.reto5);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette6(int sec){
        roulette5(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta6), sec);
        challenge = getString(R.string.reto6);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette7(int sec){
        roulette6(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta7), sec);
        challenge = getString(R.string.reto7);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void roulette8(int sec){
        roulette7(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta8), sec);
        challenge = getString(R.string.reto8);
    }
}
