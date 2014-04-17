package me.nrubin29.socialgod.quest;

import me.nrubin29.socialgod.entity.Player;
import me.nrubin29.socialgod.event.EventDispatcher;
import me.nrubin29.socialgod.event.Listener;
import me.nrubin29.socialgod.event.events.InteractWithEntityEvent;
import me.nrubin29.socialgod.event.events.MoveEvent;
import me.nrubin29.socialgod.gui.Notification;
import me.nrubin29.socialgod.map.MapManager;

import java.awt.*;

public class TestQuest extends Quest {

    public void start() {
        EventDispatcher.getInstance().registerListener(new Listener<MoveEvent>(MoveEvent.class) {
            @Override
            public void onEvent(MoveEvent e) {
                if (e.toIs(new Point(5, 5))) {
                    System.out.println("Hi");
                    requestRemove();
                }
            }
        });

        Player p = new Player("Test");

        MapManager.getInstance().getCurrentMap().getLocation(new Point(1, 1)).setEntity(p);

        EventDispatcher.getInstance().registerListener(new Listener<InteractWithEntityEvent>(InteractWithEntityEvent.class) {
            @Override
            public void onEvent(InteractWithEntityEvent e) {
                if (e.getEntity().equals(p)) {
                    System.out.println("Hi");
                    for (int i = 0; i < 5; i++) {
                        Notification.showNotification("Title", "Message");
                    }
                }
            }
        });
    }
}