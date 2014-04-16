package me.nrubin29.socialgod.tile;

import me.nrubin29.socialgod.entity.Entity;
import me.nrubin29.socialgod.event.Event;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Location {

    private final Tile tile;
    private final Point point;
    private final ArrayList<Event> events;
    private Entity entity;

    public Location(String raw, int row, int column) {
        this.tile = Tile.byID(raw);
        this.point = new Point(row, column);
        this.events = new ArrayList<>();
    }

    public Tile getTile() {
        return tile;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if (entity != null && entity.getLocation() != this) entity.setLocation(this);
    }

    public Point getPoint() {
        return point;
    }

    public void registerEvent(Event e) {
        events.add(e);
    }

    public void unregisterEvent(Event e) {
        events.remove(e);
    }

    public void checkEvents(Event.EventType type) {
        ArrayList<Event> toRemove = new ArrayList<>();

        events.stream().filter(e -> e.getType() == type).forEach(e -> {
            e.run();
            if (e.shouldRemove()) toRemove.add(e);
        });

        events.removeAll(toRemove);
    }

    @Override
    public String toString() {
        return "Location tile=" + tile + " entity={" + entity + "} point=" + point + " events=" + Arrays.toString(events.toArray());
    }
}