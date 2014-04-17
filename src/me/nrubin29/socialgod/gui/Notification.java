package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.util.UtilityProvider;

import java.awt.*;
import java.util.ArrayList;

public class Notification {

    static final ArrayList<Notification> ncs = new ArrayList<>();

    private String title, message;

    private Notification(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static void showNotification(String title, String message) {
        final Notification n = new Notification(title, message);
        ncs.add(n);

        UtilityProvider.getThreadUtil().runTimer(5 * 1000, () -> ncs.remove(n));
    }

    /*
    The current issue with notifications is that I am relying on the size of the ArrayList.
    I need to use an index or handle the painting in here.
     */
    public void paint(Graphics g) {
        g.setColor(Constants.TRANSLUCENT);

        g.fillRect(
                Frame.getInstance().getGUI().getSize().width - Constants.NOTIFICATION_DIMENSION.width,
                (ncs.size() - 1) * Constants.NOTIFICATION_DIMENSION.height,
                Constants.NOTIFICATION_DIMENSION.width,
                Constants.NOTIFICATION_DIMENSION.height
        );

        g.setColor(Color.WHITE);

        g.drawString(title, Frame.getInstance().getGUI().getSize().width - Constants.NOTIFICATION_DIMENSION.width, ncs.size() == 1 ? 12 : ncs.size() * Constants.NOTIFICATION_DIMENSION.height);
        g.drawString(message, Frame.getInstance().getGUI().getSize().width - Constants.NOTIFICATION_DIMENSION.width, ncs.size() == 1 ? 30 : ncs.size() * Constants.NOTIFICATION_DIMENSION.height + 18);
    }
}