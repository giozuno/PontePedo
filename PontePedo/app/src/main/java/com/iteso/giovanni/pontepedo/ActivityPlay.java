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
                if (card.getContentDescription() == getString(R.string.null_card)) {
                    if (stack.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Nuevas Cartas!", Toast.LENGTH_SHORT).show();
                        fillStack();
                    }
                    act = stack.peek();
                    stack.pop();
                    int time = 25;

                    if(!act.equals("Joker") && !act.equals("Joker2")) {
                        CardGame cg = dbOperations.getCardNumOfGame(act);
                        g = dbOperations.getGame(cg.getIdGame());
//                        card.setImageDrawable(getDrawable(cg.getDrawable()));
                        cardAnimation(time, cg, false);
                        card.setImageDrawable(animation);
                        animation.start();
                        card.setContentDescription(act);
                        titleGame.setText(g.getName());
                    }
                    else {
//                        card.setImageDrawable(getDrawable(R.drawable.card_joker));
                        cardAnimation(time, null, true);
                        card.setImageDrawable(animation);
                        animation.start();
                        card.setContentDescription("carta Joker");
                        titleGame.setText(R.string.game_JK);
                        button.setVisibility(View.VISIBLE);
                    }

                } else {
                    card.setImageDrawable(getDrawable(R.drawable.card_back0));
                    card.setContentDescription(getString(R.string.null_card));
                    titleGame.setText(R.string.play_getCard);
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card.getContentDescription() == getString(R.string.null_card))
                    Toast.makeText(getApplicationContext(), "Toca la carta", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(ActivityPlay.this, ActivityGameDetail.class);
                    if(!act.equals("Joker") && !act.equals("Joker2")) {
                        intent.putExtra("pos", g.getId());
                        intent.putExtra("onlyInfo", true);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Presiona el boton para ir a la ruleta", Toast.LENGTH_SHORT).show();
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
    private void cardAnimation(int sec, CardGame cg, boolean isJoker) {
        this.animation = new AnimationDrawable();
        this.animation.setOneShot(true);
        CardGame cardGame = new CardGame(false, true);
        int drawablesBack[] = cardGame.getDrawables();
        // Agregar frames de cardBack
        for(int i=0; i<=8; i++)
            this.animation.addFrame(getDrawable(drawablesBack[i]), sec);
        this.animation.addFrame(getDrawable(R.drawable.card_back9), sec);
        // Agregar frames de cardFront
        if(!isJoker)
            cardGame = cg;
        else
            cardGame = new CardGame(true, false);
        int drawables[] = cardGame.getDrawables();
        for(int i = 8; i>=0; i--)
            this.animation.addFrame(getDrawable(drawables[i]), sec);
    }
}
