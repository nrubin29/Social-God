package me.nrubin29.socialgod.map;

import java.util.ArrayList;

public class Row {

    private final ArrayList<Location> locs = new ArrayList<>();

    public Row(String raw, int row) {
        String[] split = raw.split(" ");
        for (int i = 0; i < split.length; i++) {
            locs.add(new Location(split[i], row, i));
        }
    }

    public void addLocation(Location loc) {
        locs.add(loc);
    }

    public Location locationAt(int i) {
        return locs.get(i);
    }

    public Location[] getLocations() {
        return locs.toArray(new Location[locs.size()]);
    }
}