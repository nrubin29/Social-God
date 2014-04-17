package me.nrubin29.socialgod.event.events;

import me.nrubin29.socialgod.event.Event;
import me.nrubin29.socialgod.tile.Location;

public class InteractEvent extends Event {

    private final Location location;

    public InteractEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
