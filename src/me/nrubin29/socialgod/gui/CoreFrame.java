package me.nrubin29.socialgod.gui;

import javafx.embed.swing.JFXPanel;
import me.nrubin29.socialgod.misc.Constants;

import javax.swing.*;

public class CoreFrame extends JFrame {

    private static final CoreFrame instance = new CoreFrame();
    private final GUI gui;

    private CoreFrame() {
        super(Constants.NAME + " v" + Constants.VERSION);

        add(gui = new GUI());

        new JFXPanel(); // Set up audio.

        setSize(Constants.DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static CoreFrame getInstance() {
        return instance;
    }

    public GUI getGUI() {
        return gui;
    }
}