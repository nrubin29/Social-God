package me.nrubin29.socialgod.entity;

import me.nrubin29.socialgod.map.Direction;
import me.nrubin29.socialgod.map.Location;
import me.nrubin29.socialgod.tile.Tilesheet;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Entity {

    private final HashMap<Direction, HashMap<Integer, ImageIcon>> images;

    private final ImageIcon face;
    private final HashMap<Direction, Integer> walkCycle = new HashMap<>();
    private ImageIcon currentImage;
    private Direction currentDirection;
    private Location currentLocation;

    Entity(String name) {
        images = new HashMap<>();

        Tilesheet t = new Tilesheet("sprites/" + name.toLowerCase(), "tilesheet.png");

        addImages(Direction.UP, t.getTile(new Point(0, 0)), t.getTile(new Point(1, 0)), t.getTile(new Point(2, 0)));
        addImages(Direction.RIGHT, t.getTile(new Point(3, 0)), t.getTile(new Point(4, 0)), t.getTile(new Point(0, 1)));
        addImages(Direction.LEFT, t.getTile(new Point(1, 1)), t.getTile(new Point(2, 1)), t.getTile(new Point(3, 1)));
        addImages(Direction.DOWN, t.getTile(new Point(4, 1)), t.getTile(new Point(0, 2)), t.getTile(new Point(1, 2)));

        face = UtilityProvider.getResourceUtil().getImage("sprites/" + name.toLowerCase() + "/face");

        this.currentImage = getImage(Direction.DOWN, false);
    }

    private void addImages(Direction d, ImageIcon zero, ImageIcon one, ImageIcon two) {
        HashMap<Integer, ImageIcon> intIcon = new HashMap<>();
        intIcon.put(0, zero);
        intIcon.put(1, one);
        intIcon.put(2, two);
        images.put(d, intIcon);
    }

    ImageIcon getImage(Direction d, boolean moving) {
        this.currentDirection = d;

        if (moving) {
            if (walkCycle.get(d) == null) walkCycle.put(d, 1);

            if (walkCycle.get(d) == 1) walkCycle.put(d, 2);
            else walkCycle.put(d, 1);
        }

        return images.get(d).get(moving ? walkCycle.get(d) : 0);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Location getLocation() {
        return currentLocation;
    }

    public void setLocation(Location loc) {
        if (currentLocation != null) currentLocation.setEntity(null);
        this.currentLocation = loc;
        if (loc.getEntity() != this) loc.setEntity(this);
    }

    public ImageIcon getCurrentImage() {
        return currentImage;
    }

    public ImageIcon getFace() {
        return face;
    }

    public void setCurrentImage(Direction d, boolean moving) {
        this.currentImage = getImage(d, moving);
    }

    @Override
    public String toString() {
        return "Entity type=" + getClass().getSimpleName();
    }
}