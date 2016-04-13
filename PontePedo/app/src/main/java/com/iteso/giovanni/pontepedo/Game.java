package com.iteso.giovanni.pontepedo;

/**
 * Created by giovanni on 13/04/2016.
 */
public class Game {
    private int id;
    private String text;
    private String description;

    public Game() {
    }

    public Game(int id, String text, String description) {
        this.id = id;
        this.text = text;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
