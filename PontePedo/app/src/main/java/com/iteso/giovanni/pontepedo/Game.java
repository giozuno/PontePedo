package com.iteso.giovanni.pontepedo;

/**
 * Created by giovanni on 13/04/2016.
 */
public class Game {
    private int id;
    private String name;
    private String description;
    private boolean checked;

    public Game() {
    }

    public Game(int id, String name, String description, boolean checked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.checked = checked;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
