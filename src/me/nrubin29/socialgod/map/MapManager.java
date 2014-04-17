package me.nrubin29.socialgod.map;

import me.nrubin29.socialgod.audio.AudioPlayer;
import me.nrubin29.socialgod.gui.Frame;
import me.nrubin29.socialgod.util.UtilityProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MapManager {

    private static final MapManager instance = new MapManager();
    private final ArrayList<Map> maps;
    private Map currentMap;

    private MapManager() {
        maps = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(UtilityProvider.getResourceUtil().getResourceAsStream("map/maps.txt")));

            while (reader.ready()) {
                String file = reader.readLine();
                maps.add(new Map(file));
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setCurrentMap(maps.get(0));
    }

    public static MapManager getInstance() {
        return instance;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    void setCurrentMap(Map map) {
        this.currentMap = map;
        Frame.getInstance().getGUI().getPlayer().setLocation(map.getSpawn());
        AudioPlayer.getInstance().setBackgroundMusic(map.getBackgroundMusic());
    }

    public Map getMap(String name) {
        return maps.stream().filter(map -> map.getName().equals(name)).collect(Collectors.toList()).get(0);
    }
}