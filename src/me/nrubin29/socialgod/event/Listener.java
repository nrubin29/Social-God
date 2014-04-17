package me.nrubin29.socialgod.event;

public abstract class Listener<T extends Event> {

    private final Class<T> eventClass;
    private boolean shouldRemove = false;

    public Listener(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public final Class<T> getEventClass() {
        return eventClass;
    }

    public boolean shouldRemove() {
        return shouldRemove;
    }

    public void requestRemove() {
        this.shouldRemove = true;
    }

    public abstract void onEvent(T event);
}