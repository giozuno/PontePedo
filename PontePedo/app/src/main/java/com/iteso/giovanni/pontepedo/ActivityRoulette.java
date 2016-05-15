package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ActivityRoulette extends AppCompatActivity {
    String challenge;
    AnimationDrawable animation;
    TextView tileRoulette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        final ImageButton roulette = (ImageButton) findViewById(R.id.roulette);
        tileRoulette = (TextView) findViewById(R.id.title_roulette);
        roulette.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int time1 = 150, time2 = 200, timeF = 250;
                animation = new AnimationDrawable();
                fullRoulette(time1);
                fullRoulette(time2);
                Random rnd = new Random();
                int numRnd = (int)(rnd.nextDouble() * 7);
                switch (numRnd) {
                    case 0:
                        roulette1(timeF);
                        break;
                    case 1:
                        roulette2(timeF);
                        break;
                    case 2:
                        roulette3(timeF);
                        break;
                    case 3:
                        roulette4(timeF);
                        break;
                    case 4:
                        roulette5(timeF);
                        break;
                    case 5:
                        roulette6(timeF);
                        break;
                    case 6:
                        roulette7(timeF);
                        break;
                    case 7:
                        roulette8(timeF);
                        break;
                }
                roulette.setImageDrawable(animation);
                animation.start();
                tileRoulette.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tileRoulette.setText(challenge);
                        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ding_ganador);
                        mp.start();
                    }
                }, time1*8 + time2*8 + timeF*numRnd);
                roulette.setClickable(false);
            }

        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void fullRoulette(int sec) {
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
    private void roulette1(int sec){
        this.animation.addFrame(getDrawable(R.drawable.ruleta1), sec);
        challenge = getString(R.string.reto1);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette2(int sec){
        roulette1(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta2), sec);
        challenge = getString(R.string.reto2);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette3(int sec){
        roulette2(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta3), sec);
        challenge = getString(R.string.reto3);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette4(int sec){
        roulette3(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta4), sec);
        challenge = getString(R.string.reto4);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette5(int sec){
        roulette4(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta5), sec);
        challenge = getString(R.string.reto5);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette6(int sec){
        roulette5(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta6), sec);
        challenge = getString(R.string.reto6);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette7(int sec){
        roulette6(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta7), sec);
        challenge = getString(R.string.reto7);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void roulette8(int sec){
        roulette7(sec);
        this.animation.addFrame(getDrawable(R.drawable.ruleta8), sec);
        challenge = getString(R.string.reto8);
    }
}
