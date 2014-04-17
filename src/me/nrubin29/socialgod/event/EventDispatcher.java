package me.nrubin29.socialgod.event;

import java.util.ArrayList;

public class EventDispatcher {

    private static final EventDispatcher instance = new EventDispatcher();
    private final ArrayList<Listener> listeners = new ArrayList<>();

    private EventDispatcher() {
    }

    public static EventDispatcher getInstance() {
        return instance;
    }

    public void registerListener(Listener l) {
        listeners.add(l);
    }

    public void callEvent(Event e) {
        ArrayList<Listener> remove = new ArrayList<>();

        listeners.stream().filter(l -> l.getEventClass().equals(e.getClass())).forEach(l -> {
            l.onEvent(e);
            if (l.shouldRemove()) remove.add(l);
        });

        listeners.removeAll(remove);
    }
}