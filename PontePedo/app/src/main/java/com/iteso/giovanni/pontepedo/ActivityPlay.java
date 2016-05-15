package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;

public class ActivityPlay extends AppCompatActivity {
    Stack<String> stack = new Stack<>();
    String carta[] = {"AsR", "2R", "3R", "4R", "5R", "6R", "7R", "8R", "9R", "10R", "JR", "QR", "KR", "Joker", "Joker2"};
    Game g = null;
    String act;
    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        final DataBaseOperations dbOperations = new DataBaseOperations(getApplicationContext());

        final ImageButton card = (ImageButton) findViewById(R.id.play_card);
        final ImageButton info = (ImageButton) findViewById(R.id.play_info);
        final TextView titleGame = (TextView) findViewById(R.id.play_name_of_game);
        final Button button = (Button) findViewById(R.id.buttonRoulette);
        button.setVisibility(View.INVISIBLE);
        card.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                int time = 25;
                CardGame cg = null;
                if (card.getContentDescription() == getString(R.string.null_card)) {
                    if (stack.isEmpty()) {
                        Toast.makeText(getApplicationContext(), R.string.play_toast_newCards_message, Toast.LENGTH_SHORT).show();
                        fillStack();
                    }
                    act = stack.peek();
                    stack.pop();

                    if(!act.equals(getString(R.string.card_Joker)) && !act.equals(getString(R.string.card_Joker2))) {
                        cg = dbOperations.getCardNumOfGame(act);
                        g = dbOperations.getGame(cg.getIdGame());
                        setBackAnimation(time, cg, false);
                        card.setImageDrawable(animation);
                        animation.start();
                        card.setContentDescription(act);
                        titleGame.setText(g.getName());
                    }
                    else {
                        setBackAnimation(time, cg, true);
                        card.setImageDrawable(animation);
                        animation.start();
                        card.setContentDescription(getString(R.string.desc_card_joker));
                        titleGame.setText(R.string.play_game_roulette);
                        button.setVisibility(View.VISIBLE);
                    }

                } else {
//                    card.setImageDrawable(getDrawable(R.drawable.card_back0));
                    boolean isJoker = false;
                    if(act.equals(getString(R.string.card_Joker)) || act.equals(getString(R.string.card_Joker2)))
                        isJoker = true;
                    else
                        cg = dbOperations.getCardNumOfGame(act);
                    setFrontAnimation(time, cg, isJoker);
                    card.setImageDrawable(animation);
                    animation.start();
                    card.setContentDescription(getString(R.string.null_card));
                    titleGame.setText(R.string.play_getCard);
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card.getContentDescription() == getString(R.string.null_card))
                    Toast.makeText(getApplicationContext(), R.string.play_toast_touchCard, Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(ActivityPlay.this, ActivityGameDetail.class);
                    if(!act.equals(getString(R.string.card_Joker)) && !act.equals(getString(R.string.card_Joker2))) {
                        intent.putExtra(getString(R.string.intent_pos), g.getId());
                        intent.putExtra(getString(R.string.intent_onlyInfo), true);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), R.string.play_toast_goToRoulette, Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPlay.this, ActivityRoulette.class);
                startActivity(intent);
            }
        });
    }

    private void fillStack() {
        for(int i=0; i<carta.length; i++){
            boolean flag = false;
            Random rnd = new Random();
            int x = (int)(rnd.nextDouble() * carta.length);
            while (!flag){
                if(!stack.contains(carta[x])){
                    stack.push(carta[x]);
                    flag = true;
                }
                else if (x == carta.length-1)
                    x = 0;
                else
                    x++;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setBackAnimation(int sec, CardGame cg, boolean isJoker) {
        this.animation = new AnimationDrawable();
        this.animation.setOneShot(true);
        CardGame cardGame = new CardGame(false, true);
        int drawablesBack[] = cardGame.getBackDrawables();
        // Agregar frames del cardBack
        for(int i=0; i<=8; i++)
            this.animation.addFrame(getDrawable(drawablesBack[i]), sec);
        this.animation.addFrame(getDrawable(R.drawable.card_back9), sec);
        // Agregar frames del cardFront
        if(!isJoker)
            cardGame = cg;
        else
            cardGame = new CardGame(true, true);
        int drawables[] = cardGame.getBackDrawables();
        for(int i = 8; i>=0; i--)
            this.animation.addFrame(getDrawable(drawables[i]), sec);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setFrontAnimation(int sec, CardGame cg, boolean isJoker) {
        this.animation = new AnimationDrawable();
        this.animation.setOneShot(true);
        CardGame cardGame;
        if(isJoker)
            cardGame = new CardGame(true, false);
        else
            cardGame = cg;
        int drawables[] = cardGame.getFrontDrawables();
        for(int i=0; i<6; i++)
            animation.addFrame(getDrawable(drawables[i]), sec);
        animation.addFrame(getDrawable(R.drawable.card_back0), sec);
    }
}
