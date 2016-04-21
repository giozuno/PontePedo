package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
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
                        Toast.makeText(getApplicationContext(), "Se ha barajeado", Toast.LENGTH_SHORT).show();
                        fillStack();
                    }
                    act = stack.peek();
                    stack.pop();

                    if(act != "Joker" && act != "Joker2") {
                        CardGame cg = dbOperations.getCardNumOfGame(act);
                        g = dbOperations.getGame(cg.getIdGame());
                        card.setImageDrawable(getDrawable(cg.getDrawable()));
                        card.setContentDescription(act);
                        titleGame.setText(g.getName());
                    }
                    else {
                        card.setImageDrawable(getDrawable(R.drawable.card_joker));
                        card.setContentDescription("carta Joker");
                        titleGame.setText(R.string.game_JK);
                        button.setVisibility(View.VISIBLE);
                    }
                } else {
                    card.setImageDrawable(getDrawable(R.drawable.card_back));
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
                    intent.putExtra("pos", g.getId());
                    startActivity(intent);
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

    public void fillStack() {
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
}
