package me.nrubin29.socialgod.keycommand.commands;

import me.nrubin29.socialgod.gui.CoreFrame;
import me.nrubin29.socialgod.gui.GUI;
import me.nrubin29.socialgod.keycommand.Key;
import me.nrubin29.socialgod.keycommand.KeyCommand;
import me.nrubin29.socialgod.map.Direction;

import java.awt.event.KeyEvent;

public class Move extends KeyCommand {

    public Move(GUI gui) {
        super(
                gui,
                new Key(KeyEvent.VK_UP, false, false, false),
                new Key(KeyEvent.VK_DOWN, false, false, false),
                new Key(KeyEvent.VK_LEFT, false, false, false),
                new Key(KeyEvent.VK_RIGHT, false, false, false)
        );
    }

    public void run(Key key) {
        CoreFrame.getInstance().getGUI().movement(Direction.valueOf(key.getKey()));
    }
}