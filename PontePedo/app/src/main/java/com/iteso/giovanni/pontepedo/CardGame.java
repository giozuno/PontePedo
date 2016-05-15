package com.iteso.giovanni.pontepedo;

/**
 * Created by giovanni on 15/04/2016.
 */
public class CardGame {
    private int id;
    private String name;
    private int drawable_0, drawable_1, drawable_2, drawable_3, drawable_4, drawable_5, drawable_6, drawable_7, drawable_8;
    private int drawable_f1;
    private int drawable_f2;
    private int drawable_f3;
    private int drawable_f4;
    private int drawable_f5;
    private int drawable_f6;
    private int idGame;

    public CardGame() {
    }

    public CardGame(int id, String name, int drawable_0, int drawable_1, int drawable_2, int drawable_3, int drawable_4,
                    int drawable_5, int drawable_6, int drawable_7, int drawable_8,  int drawable_f1, int drawable_f2,
                    int drawable_f3, int drawable_f4, int drawable_f5, int drawable_f6,int idGame) {
        this.id = id;
        this.name = name;
        this.drawable_0 = drawable_0;
        this.drawable_1 = drawable_1;
        this.drawable_2 = drawable_2;
        this.drawable_3 = drawable_3;
        this.drawable_4 = drawable_4;
        this.drawable_5 = drawable_5;
        this.drawable_6 = drawable_6;
        this.drawable_7 = drawable_7;
        this.drawable_8 = drawable_8;
        this.drawable_f1 = drawable_f1;
        this.drawable_f2 = drawable_f2;
        this.drawable_f3 = drawable_f3;
        this.drawable_f4 = drawable_f4;
        this.drawable_f5 = drawable_f5;
        this.drawable_f6 = drawable_f6;
        this.idGame = idGame;
    }

    public CardGame(boolean isJoker, boolean isBack) {
        if(isBack && isJoker) {
            this.drawable_0 = R.drawable.card_joker_0;
            this.drawable_1 = R.drawable.card_joker_1;
            this.drawable_2 = R.drawable.card_joker_2;
            this.drawable_3 = R.drawable.card_joker_3;
            this.drawable_4 = R.drawable.card_joker_4;
            this.drawable_5 = R.drawable.card_joker_5;
            this.drawable_6 = R.drawable.card_joker_6;
            this.drawable_7 = R.drawable.card_joker_7;
            this.drawable_8 = R.drawable.card_joker_8;
        }
        else if(isBack) {
            this.drawable_0 = R.drawable.card_back0;
            this.drawable_1 = R.drawable.card_back1;
            this.drawable_2 = R.drawable.card_back2;
            this.drawable_3 = R.drawable.card_back3;
            this.drawable_4 = R.drawable.card_back4;
            this.drawable_5 = R.drawable.card_back5;
            this.drawable_6 = R.drawable.card_back6;
            this.drawable_7 = R.drawable.card_back7;
            this.drawable_8 = R.drawable.card_back8;
        }
        else if(isJoker) {
            this.drawable_f1 = R.drawable.card_joker_f1;
            this.drawable_f2 = R.drawable.card_joker_f2;
            this.drawable_f3 = R.drawable.card_joker_f3;
            this.drawable_f4 = R.drawable.card_joker_f4;
            this.drawable_f5 = R.drawable.card_joker_f5;
            this.drawable_f6 = R.drawable.card_joker_f6;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getBackDrawables() {
        int drawable[] = new int[9];
        drawable[0] = this.drawable_0;
        drawable[1] = this.drawable_1;
        drawable[2] = this.drawable_2;
        drawable[3] = this.drawable_3;
        drawable[4] = this.drawable_4;
        drawable[5] = this.drawable_5;
        drawable[6] = this.drawable_6;
        drawable[7] = this.drawable_7;
        drawable[8] = this.drawable_8;
        return drawable;
    }

    public int[] getFrontDrawables() {
        int drawable[] = new int[6];
        drawable[0] = this.drawable_f1;
        drawable[1] = this.drawable_f2;
        drawable[2] = this.drawable_f3;
        drawable[3] = this.drawable_f4;
        drawable[4] = this.drawable_f5;
        drawable[5] = this.drawable_f6;
        return drawable;
    }

    public void setDrawable_0(int drawable_0) {
        this.drawable_0 = drawable_0;
    }

    public void setDrawable_1(int drawable_1) {
        this.drawable_1 = drawable_1;
    }

    public void setDrawable_2(int drawable_2) {
        this.drawable_2 = drawable_2;
    }

    public void setDrawable_3(int drawable_3) {
        this.drawable_3 = drawable_3;
    }

    public void setDrawable_4(int drawable_4) {
        this.drawable_4 = drawable_4;
    }

    public void setDrawable_5(int drawable_5) {
        this.drawable_5 = drawable_5;
    }

    public void setDrawable_6(int drawable_6) {
        this.drawable_6 = drawable_6;
    }

    public void setDrawable_7(int drawable_7) {
        this.drawable_7 = drawable_7;
    }

    public void setDrawable_8(int drawable_8) {
        this.drawable_8 = drawable_8;
    }

    public void setDrawable_f1(int drawable_f1) {
        this.drawable_f1 = drawable_f1;
    }

    public void setDrawable_f2(int drawable_f2) {
        this.drawable_f2 = drawable_f2;
    }

    public void setDrawable_f3(int drawable_f3) {
        this.drawable_f3 = drawable_f3;
    }

    public void setDrawable_f4(int drawable_f4) {
        this.drawable_f4 = drawable_f4;
    }

    public void setDrawable_f5(int drawable_f5) {
        this.drawable_f5 = drawable_f5;
    }

    public void setDrawable_f6(int drawable_f6) {
        this.drawable_f6 = drawable_f6;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }
}
