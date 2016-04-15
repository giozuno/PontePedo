package com.iteso.giovanni.pontepedo;

/**
 * Created by giovanni on 15/04/2016.
 */
public class CardGame {
    private int id;
    private String name;
    private int drawable;
    private int idGame;

    public CardGame() {
    }

    public CardGame(int id, String name, int drawable, int idGame) {
        this.id = id;
        this.name = name;
        this.drawable = drawable;
        this.idGame = idGame;
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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }
}
