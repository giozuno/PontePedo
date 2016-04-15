package com.iteso.giovanni.pontepedo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class ActivityPlay extends AppCompatActivity {
    Stack<String> stack = new Stack<>();
    String carta[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "JK"};
    String act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
                    switch (act) {
                        case "A":
                            card.setImageDrawable(getDrawable(R.drawable.card_a));
                            card.setContentDescription("carta A");
                            titleGame.setText(R.string.game_A);
                            break;
                        case "2":
                            card.setImageDrawable(getDrawable(R.drawable.card_2));
                            card.setContentDescription("carta 2");
                            titleGame.setText(R.string.game_2);
                            break;
                        case "3":
                            card.setImageDrawable(getDrawable(R.drawable.card_3));
                            card.setContentDescription("carta 3");
                            titleGame.setText(R.string.game_3);
                            break;
                        case "4":
                            card.setImageDrawable(getDrawable(R.drawable.card_4));
                            card.setContentDescription("carta 4");
                            titleGame.setText(R.string.game_4);
                            break;
                        case "5":
                            card.setImageDrawable(getDrawable(R.drawable.card_5));
                            card.setContentDescription("carta 5");
                            titleGame.setText(R.string.game_5);
                            break;
                        case "6":
                            card.setImageDrawable(getDrawable(R.drawable.card_6));
                            card.setContentDescription("carta 6");
                            titleGame.setText(R.string.game_6);
                            break;
                        case "7":
                            card.setImageDrawable(getDrawable(R.drawable.card_7));
                            card.setContentDescription("carta 7");
                            titleGame.setText(R.string.game_7);
                            break;
                        case "8":
                            card.setImageDrawable(getDrawable(R.drawable.card_8));
                            card.setContentDescription("carta 8");
                            titleGame.setText(R.string.game_8);
                            break;
                        case "9":
                            card.setImageDrawable(getDrawable(R.drawable.card_9));
                            card.setContentDescription("carta 9");
                            titleGame.setText(R.string.game_9);
                            break;
                        case "10":
                            card.setImageDrawable(getDrawable(R.drawable.card_10));
                            card.setContentDescription("carta 10");
                            titleGame.setText(R.string.game_10);
                            break;
                        case "J":
                            card.setImageDrawable(getDrawable(R.drawable.card_j));
                            card.setContentDescription("carta J");
                            titleGame.setText(R.string.game_J);
                            break;
                        case "Q":
                            card.setImageDrawable(getDrawable(R.drawable.card_q));
                            card.setContentDescription("carta Q");
                            titleGame.setText(R.string.game_Q);
                            break;
                        case "K":
                            card.setImageDrawable(getDrawable(R.drawable.card_k));
                            card.setContentDescription("carta K");
                            titleGame.setText(R.string.game_K);
                            break;
                        case "JK":
                            card.setImageDrawable(getDrawable(R.drawable.card_joker));
                            card.setContentDescription("carta Joker");
                            titleGame.setText(R.string.game_JK);
                            button.setVisibility(View.VISIBLE);
                            break;
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
                if(card.getContentDescription() == getString(R.string.null_card))
                    Toast.makeText(getApplicationContext(), "Toca la carta", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(ActivityPlay.this, ActivityGameDetail.class);
                    intent.putExtra("value", Arrays.asList(carta).indexOf(act));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_play, menu);
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

    public void fillStack() {
        for(int i=0; i<14; i++){
            boolean flag = false;
            Random rnd = new Random();
            int x = (int)(rnd.nextDouble() * 14);
            while (!flag){
                if(!stack.contains(carta[x])){
                    stack.push(carta[x]);
                    flag = true;
                }
                else if (x == 13)
                    x = 0;
                else
                    x++;
            }
        }
    }
}
