package me.nrubin29.socialgod.tile;

import me.nrubin29.socialgod.util.UtilityProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TilesheetManager {

    private static final TilesheetManager instance = new TilesheetManager();
    private final ArrayList<Tilesheet> tilesheets = new ArrayList<>();

    private TilesheetManager() {
    }

    public static TilesheetManager getInstance() {
        return instance;
    }

    public void setup() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(UtilityProvider.getResourceUtil().getResourceAsStream("tilesheet/names.txt")));

            while (reader.ready()) {
                String file = reader.readLine();
                tilesheets.add(new Tilesheet("tilesheet", file));
            }

            reader.close();
        } catch (Exception ignored) {
        }
    }

    public Tilesheet getTilesheet(String name) {
        for (Tilesheet t : tilesheets) {
            if (t.getName().equalsIgnoreCase(name)) return t;
        }
        return null;
    }
}