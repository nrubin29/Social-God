package me.nrubin29.socialgod.map;

import me.nrubin29.socialgod.tile.Tile;

public enum MapType {

    CITY(Tile.GRASS),
    ROUTE(Tile.GRASS),
    CAVE(Tile.GRASS),
    BUILDING(Tile.GRASS);

    private final Tile backgroundTile;

    MapType(Tile tile) {
        this.backgroundTile = tile;
    }

    public Tile getBackgroundTile() {
        return backgroundTile;
    }
}