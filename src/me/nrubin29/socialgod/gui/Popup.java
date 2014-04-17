package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Popup extends JPanel {

    private static final long serialVersionUID = 1L;

    private Popup(ImageIcon image, JComponent comp, final Runnable whenDone) {
        if (image != null) {
            JLabel img = new JLabel(UtilityProvider.getResourceUtil().resizeImage(image, 75, 60));
            img.setAlignmentX(Component.CENTER_ALIGNMENT);
            img.setFocusable(false);
            add(img);
        }

        JLabel x = new JLabel("X");
        x.setForeground(Color.RED);
        x.setCursor(new Cursor(Cursor.HAND_CURSOR));
        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Popup.this.setVisible(false);
                Frame.getInstance().getGUI().remove(Popup.this);
                Frame.getInstance().getGUI().setInputEnabled(true);

                if (whenDone != null) whenDone.run();
            }
        });

        add(comp);
        add(x);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(new Rectangle(
                (Constants.DIMENSION.width / 2) - (Constants.POPUP_DIMENSION.width / 2),
                (Constants.DIMENSION.height / 2) - (Constants.POPUP_DIMENSION.height / 2),
                Constants.POPUP_DIMENSION.width,
                Constants.POPUP_DIMENSION.height
        ));
    }

    void add() {
        Frame.getInstance().getGUI().add(this);
        Frame.getInstance().getGUI().setInputEnabled(false);
    }

    public static class PopupFactory {
        private final ArrayList<Popup> popups = new ArrayList<>();
        private Runnable whenDone;
        private int cursor;

        public PopupFactory addPopup(ImageIcon image, String text) {
            JPanel panel = new JPanel();
            panel.setFocusable(false);
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextArea textArea = new JTextArea(text);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textArea.setFocusable(false);
            textArea.setBorder(null);
            textArea.setForeground(Color.WHITE);
            textArea.setBackground(Color.GRAY);
            textArea.setPreferredSize(new Dimension(Constants.POPUP_DIMENSION.width, 40));
            textArea.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(textArea);

            addCustom(image, panel);

            return this;
        }

        public PopupFactory addCustom(ImageIcon image, JComponent comp) {
            popups.add(new Popup(image, comp, () -> {
                cursor++;
                if (popups.size() <= cursor) {
                    if (whenDone != null) whenDone.run();
                    return;
                }
                popups.get(cursor).add();
            }));
            return this;
        }

        public PopupFactory setWhenDone(Runnable whenDone) {
            this.whenDone = whenDone;
            return this;
        }

        public void show() {
            popups.get(0).add();
        }
    }
}