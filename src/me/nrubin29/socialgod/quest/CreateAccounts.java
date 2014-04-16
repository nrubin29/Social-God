package me.nrubin29.socialgod.quest;

import me.nrubin29.socialgod.event.Event;
import me.nrubin29.socialgod.map.MapManager;

public class CreateAccounts extends Quest {

    public void start() {
        MapManager.getInstance().getCurrentMap().getLocation(0, 0).registerEvent(new Event(Event.EventType.INTERACT, true, "testing"));
    }
}