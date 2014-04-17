package me.nrubin29.socialgod.tile;

import me.nrubin29.socialgod.entity.Entity;

import java.awt.*;

public class Location {

    private final Tile tile;
    private final Point point;
    private Entity entity;

    public Location(String raw, int row, int column) {
        this.tile = Tile.byID(raw);
        this.point = new Point(row, column);
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

    @Override
    public String toString() {
        return "Location tile=" + tile + " entity={" + entity + "} point=" + point;
    }
}