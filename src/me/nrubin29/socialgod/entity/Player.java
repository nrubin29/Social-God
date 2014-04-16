package me.nrubin29.socialgod.entity;

public class Player extends Entity {

    private final String name;

    public Player(String name) {
        super("Player");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}