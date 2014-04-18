package me.nrubin29.socialgod.misc;

import java.awt.*;

public class Constants {

    /* GUI */
    public static final Dimension DIMENSION = new Dimension(640, 500);
    public static final Dimension SOCIAL_DIMENSION = new Dimension(300, 110);
    public static final Dimension POPUP_DIMENSION = new Dimension(300, 130);
    public static final Dimension NOTIFICATION_DIMENSION = new Dimension(250, 40);
    public static final Dimension ERROR_DIMENSION = new Dimension(300, 250);

    public static final int
            NUM_ROWS = 15,
            TILES_PER_ROW = 20,
            TILES_PER_COLUMN = 15,
            DEFAULT_TILE_DIM = 32;

    public static final int
            TILE_WIDTH = DEFAULT_TILE_DIM;
    public static final int TILE_HEIGHT = DEFAULT_TILE_DIM;

    public static final Color
            TRANSPARENT = new Color(0, 0, 0, 0),
            TRANSLUCENT = new Color(0, 0, 0, 50);

    /* Information */
    public static final String
            NAME = "SocialGod",
            VERSION = "0.0.1",
            AUTHOR = "Noah Rubin";
}