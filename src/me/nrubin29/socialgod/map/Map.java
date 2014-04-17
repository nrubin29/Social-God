package me.nrubin29.socialgod.map;

import me.nrubin29.socialgod.audio.Music;
import me.nrubin29.socialgod.tile.Location;
import me.nrubin29.socialgod.tile.Row;
import me.nrubin29.socialgod.util.UtilityProvider;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class Map {

    private final ArrayList<Row> rows;
    private String name;
    private MapType type;
    private Music backgroundMusic;
    private Location spawn;

    Map(String file) {
        rows = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(UtilityProvider.getResourceUtil().getResourceAsStream("map/" + file)));

            this.name = reader.readLine();
            this.type = MapType.valueOf(reader.readLine());
            this.backgroundMusic = Music.valueOf(reader.readLine());

            String spawnLine = reader.readLine();
            int spawnRow = Integer.valueOf(spawnLine.split(" ")[0]);
            int spawnColumn = Integer.valueOf(spawnLine.split(" ")[1]);

            int i = 0;

            while (reader.ready()) {
                rows.add(new Row(reader.readLine(), i++));
            }

            this.spawn = getLocation(spawnRow, spawnColumn);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public MapType getType() {
        return type;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    public Row getRow(int row) {
        return rows.get(row);
    }

    public Location getLocation(Point p) {
        return rows.get(p.x).locationAt(p.y);
    }

    Location getLocation(int row, int column) {
        return getLocation(new Point(row, column));
    }

    public Location getSpawn() {
        return spawn;
    }
}