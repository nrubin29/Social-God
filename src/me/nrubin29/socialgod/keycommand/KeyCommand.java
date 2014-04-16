package me.nrubin29.socialgod.keycommand;

import me.nrubin29.socialgod.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class KeyCommand {

    protected KeyCommand(GUI gui, final Key... keys) {
        for (final Key key : keys) {
            gui.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key.getKey(), key.getKeySum()), key.getKey());
            gui.getActionMap().put(key.getKey(), new AbstractAction() {
                private static final long serialVersionUID = 1L;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!KeyCommandManager.getInstance().isInputEnabled()) return;
                    run(key);
                }
            });
        }
    }

    protected abstract void run(Key key);
}