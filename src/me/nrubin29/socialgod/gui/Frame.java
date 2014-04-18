package me.nrubin29.socialgod.gui;

import javafx.embed.swing.JFXPanel;
import me.nrubin29.socialgod.misc.Constants;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {

    private static final Frame instance = new Frame();
    private final GUI gui;

    private Frame() {
        super(Constants.NAME + " v" + Constants.VERSION);

        add(gui = new GUI());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gui.keyPressed = e.getKeyCode();
                gui.metaPressed = e.isMetaDown() || e.isControlDown();
            }
        });

        new JFXPanel(); // Set up audio.

        setSize(Constants.DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static Frame getInstance() {
        return instance;
    }

    public GUI getGUI() {
        return gui;
    }
}