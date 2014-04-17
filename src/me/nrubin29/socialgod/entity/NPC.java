package me.nrubin29.socialgod.entity;

import me.nrubin29.socialgod.tile.Location;

public class NPC extends Entity {

    public NPC(NPCType type, Location loc) {
        super(type.getType());
        setLocation(loc);
    }

    public enum NPCType {
        BOY,
        GIRL;

        public String getType() {
            return name().toLowerCase();
        }
    }
}