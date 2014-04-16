package me.nrubin29.socialgod.keycommand.commands;

import me.nrubin29.socialgod.event.Event.EventType;
import me.nrubin29.socialgod.gui.GUI;
import me.nrubin29.socialgod.keycommand.Key;
import me.nrubin29.socialgod.keycommand.KeyCommand;
import me.nrubin29.socialgod.map.Direction;
import me.nrubin29.socialgod.map.MapManager;
import me.nrubin29.socialgod.misc.Session;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Interact extends KeyCommand {

    public Interact(GUI gui) {
        super(
                gui,
                new Key(KeyEvent.VK_ENTER, false, false, false)
        );
    }

    public void run(Key key) {
        final Direction d = Session.getInstance().getPlayer().getCurrentDirection();

        MapManager.getInstance().getCurrentMap().getLocation(
                new Point(
                        Session.getInstance().getPlayer().getLocation().getPoint().x + d.getMovement().x,
                        Session.getInstance().getPlayer().getLocation().getPoint().y + d.getMovement().y
                )
        ).checkEvents(EventType.INTERACT);
    }
}