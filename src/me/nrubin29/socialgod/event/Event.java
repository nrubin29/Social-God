package me.nrubin29.socialgod.event;

public class Event {

    private final EventType type;
    private final String[] cmds;
    private boolean isEnabled, shouldRemove;

    public Event(EventType type, boolean isEnabled, String... cmds) {
        this.type = type;
        this.cmds = cmds;
        this.isEnabled = isEnabled;
    }

    public EventType getType() {
        return type;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean shouldRemove() {
        return shouldRemove;
    }

    public void requestRemoval() {
        setEnabled(false);
        shouldRemove = true;
    }

    public void run() {
        if (!isEnabled) return;

        for (String cmd : cmds) {
            System.out.println(cmd);
        }
    }

    public enum EventType {MOVE, INTERACT}
}