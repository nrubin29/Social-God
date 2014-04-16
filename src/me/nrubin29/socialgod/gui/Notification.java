package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Notification extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final ArrayList<Notification> ncs = new ArrayList<>();

    private Notification(String title, String message) {
        JLabel t = new JLabel(title);
        t.setFont(UtilityProvider.getFontUtil().getFont(10));
        t.setForeground(Color.WHITE);

        JLabel m = new JLabel(message);
        m.setForeground(Color.WHITE);

        add(t);
        add(m);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Constants.TRANSLUCENT);
        setBounds(CoreFrame.getInstance().getGUI().getSize().width - Constants.NOTIFICATION_DIMENSION.width, ncs.size() * Constants.NOTIFICATION_DIMENSION.height, Constants.NOTIFICATION_DIMENSION.width, Constants.NOTIFICATION_DIMENSION.height);
    }

    public static void showNotification(String title, String message) {
        final Notification n = new Notification(title, message);
        CoreFrame.getInstance().getGUI().add(n);
        ncs.add(n);

        UtilityProvider.getThreadUtil().runTimer(5 * 1000, () -> {
            CoreFrame.getInstance().getGUI().remove(n);
            ncs.remove(n);
        });
    }
}