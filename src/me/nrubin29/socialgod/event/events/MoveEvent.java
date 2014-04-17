package me.nrubin29.socialgod.event.events;

import me.nrubin29.socialgod.event.Event;
import me.nrubin29.socialgod.gui.Frame;
import me.nrubin29.socialgod.tile.Location;

import java.awt.*;

public class MoveEvent extends Event {

    private final Location from;

    public MoveEvent(Location from) {
        this.from = from;
    }

    public Location getFrom() {
        return from;
    }

    public boolean toIs(Point p) {
        return Frame.getInstance().getGUI().getPlayer().getLocation().getPoint().equals(p);
    }

    public boolean fromIs(Point p) {
        return from.getPoint().equals(p);
    }
}