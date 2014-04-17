package me.nrubin29.socialgod.event.events;

import me.nrubin29.socialgod.entity.Entity;
import me.nrubin29.socialgod.tile.Location;

public class InteractWithEntityEvent extends InteractEvent {

    private Entity entity;

    public InteractWithEntityEvent(Location location, Entity entity) {
        super(location);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
