package com.iteso.giovanni.pontepedo;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by giovanni on 23/03/2016.
 */
public class Games extends AppCompatActivity {
    String gameTitle[];
    String gameDescription[];

    public Games(){
        gameTitle = new String[] {getString(R.string.game_A), getString(R.string.game_2), getString(R.string.game_3),
                getString(R.string.game_4), getString(R.string.game_5), getString(R.string.game_6), getString(R.string.game_7),
                getString(R.string.game_8), getString(R.string.game_9), getString(R.string.game_10), getString(R.string.game_J),
                getString(R.string.game_Q), getString(R.string.game_K)};
        gameDescription = new String[] {getString(R.string.desc_A), getString(R.string.desc_2), getString(R.string.desc_3),
                getString(R.string.desc_4), getString(R.string.desc_5), getString(R.string.desc_6), getString(R.string.desc_7),
                getString(R.string.desc_8), getString(R.string.desc_9), getString(R.string.desc_10), getString(R.string.desc_J),
                getString(R.string.desc_Q), getString(R.string.desc_K)};
    }

    public String[] copyString(String des){
        String response[]={};
        if(des == "title")
            response = gameTitle.clone();
        else if(des == "desc")
            response = gameDescription.clone();
        return response;
    }

    public String getTitle(int pos){
        return gameTitle[pos];
    }

    public String getDescription(int pos){
        return gameDescription[pos];
    }

    public void setTitle(String newGame, int pos){
        gameTitle[pos] = newGame;
    }

    public void setDescription(String newDesc, int pos){
        gameDescription[pos] = newDesc;
    }

}
