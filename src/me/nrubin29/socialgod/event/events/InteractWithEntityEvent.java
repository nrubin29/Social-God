package me.nrubin29.socialgod.event.events;

import me.nrubin29.socialgod.entity.Entity;
import me.nrubin29.socialgod.map.Location;

public class InteractWithEntityEvent extends InteractEvent {

    private final Entity entity;

    public InteractWithEntityEvent(Location location, Entity entity) {
        super(location);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
