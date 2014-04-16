package me.nrubin29.socialgod.keycommand;

import me.nrubin29.socialgod.gui.GUI;
import me.nrubin29.socialgod.keycommand.commands.Interact;
import me.nrubin29.socialgod.keycommand.commands.Move;

import java.util.ArrayList;

public class KeyCommandManager {

    private static final KeyCommandManager instance = new KeyCommandManager();
    private final ArrayList<KeyCommand> cmds = new ArrayList<>();
    private boolean inputEnabled = true;

    private KeyCommandManager() {
    }

    public static KeyCommandManager getInstance() {
        return instance;
    }

    public void setup(GUI gui) {
        cmds.add(new Interact(gui));
        cmds.add(new Move(gui));
    }

    public boolean isInputEnabled() {
        return inputEnabled;
    }

    public void setInputEnabled(boolean inputEnabled) {
        this.inputEnabled = inputEnabled;
    }
}