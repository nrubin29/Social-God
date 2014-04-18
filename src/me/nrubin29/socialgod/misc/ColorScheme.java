package me.nrubin29.socialgod.misc;

import java.awt.*;

public class ColorScheme {

    private final Color background;
    private final Color text;

    public ColorScheme(Color background, Color text) {
        this.background = background;
        this.text = text;
    }

    public Color getBackground() {
        return background;
    }

    public Color getText() {
        return text;
    }
}