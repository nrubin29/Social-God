package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.audio.AudioPlayer;
import me.nrubin29.socialgod.audio.SoundEffect;
import me.nrubin29.socialgod.misc.ColorScheme;
import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.ArrayList;

public class Notification extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final ArrayList<Notification> ncs = new ArrayList<>();

    private Notification(String title, String message, ColorScheme colorScheme) {
        JLabel t = new JLabel(title);
        t.setFont(UtilityProvider.getFontUtil().getFont(10));
        t.setForeground(colorScheme.getText());

        JLabel m = new JLabel(message);
        m.setForeground(colorScheme.getText());

        add(Box.createVerticalStrut(5));
        add(t);
        add(m);

        setBorder(new MatteBorder(2, 2, 2, 2, colorScheme.getText()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(colorScheme.getBackground());
        setSize(Constants.NOTIFICATION_DIMENSION);
        setLocation(
                Frame.getInstance().getGUI().getSize().width - Constants.NOTIFICATION_DIMENSION.width,
                ncs.size() * Constants.NOTIFICATION_DIMENSION.height
        );
    }

    public static void showNotification(String title, String message, ColorScheme colorScheme) {
        final Notification n = new Notification(title, message, colorScheme);
        Frame.getInstance().getGUI().add(n);
        ncs.add(n);
        AudioPlayer.getInstance().playSoundEffect(SoundEffect.CLICK);

        UtilityProvider.getThreadUtil().runTimer(5 * 1000, () -> {
            Frame.getInstance().getGUI().remove(n);
            ncs.remove(n);
        });
    }
}