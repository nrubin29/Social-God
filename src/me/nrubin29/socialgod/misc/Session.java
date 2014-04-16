package me.nrubin29.socialgod.misc;

import me.nrubin29.socialgod.entity.Player;

public class Session {

    private static final Session instance = new Session();
    private final Player player;

    private Session() {
        player = new Player("Player");
    }

    public static Session getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }
}