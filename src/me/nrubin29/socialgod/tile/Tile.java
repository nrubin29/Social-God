package me.nrubin29.socialgod.tile;

import me.nrubin29.socialgod.misc.Image;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;

public enum Tile implements Image {

    EMPTY,

    GRASS("environment", "G", 1, 1, Layer.BELOW),
    TREE_TALL_TOP_LEFT("environment", "TTTL", 2, 1, Layer.ABOVE),
    TREE_TALL_TOP_RIGHT("environment", "TTTR", 3, 1, Layer.ABOVE),
    COFFIN_DOWN("environment", "CD", 4, 1, Layer.ON),
    COFFIN_LEFT("environment", "CL", 5, 1, Layer.ON),

    WATER_VERTICAL("environment", "WV", 1, 2, Layer.ON),
    TREE_TALL_MIDDLE_LEFT("environment", "TTML", 2, 2, Layer.ABOVE),
    TREE_TALL_MIDDLE_RIGHT("environment", "TTMR", 3, 2, Layer.ABOVE),
    COFFIN_RIGHT("environment", "CR", 4, 2, Layer.ON),
    COFFIN_UP("environment", "CU", 5, 2, Layer.ON),

    WATER_HORIZONTAL("environment", "WH", 1, 3, Layer.ON),
    TREE_TALL_BOTTOM_LEFT("environment", "TTBL", 2, 3, Layer.ON),
    TREE_TALL_BOTTOM_RIGHT("environment", "TTBR", 3, 3, Layer.ON),
    FENCE_HORIZONTAL("environment", "FH", 4, 3, Layer.ON),
    FENCE_VERTICAL("environment", "FV", 5, 3, Layer.ON),

    FENCE_CORNER_TOP_LEFT("environment", "FCTL", 4, 4, Layer.ON),
    FENCE_CORNER_TOP_RIGHT("environment", "FCTR", 5, 4, Layer.ON),

    FENCE_CORNER_BOTTOM_LEFT("environment", "FCBL", 4, 5, Layer.ON),
    FENCE_CORNER_BOTTOM_RIGHT("environment", "FCBR", 5, 5, Layer.ON),

    PATH_END_HORIZONTAL("environment", "PEH", 2, 7, Layer.BELOW),
    PATH_END_VERTICAL("environment", "PEV", 3, 7, Layer.BELOW),

    PATH_CORNER_TOP_LEFT("environment", "PCTL", 2, 8, Layer.BELOW),
    PATH_CORNER_TOP_RIGHT("environment", "PCTR", 3, 8, Layer.BELOW),

    PATH_CORNER_BOTTOM_LEFT("environment", "PCBL", 2, 9, Layer.BELOW),
    PATH_CORNER_BOTTOM_RIGHT("environment", "PCBR", 3, 9, Layer.BELOW);

    private final String id;
    private ImageIcon image;
    private Layer layer;

    Tile(String tilesheet, String id, int x, int y, Layer layer) {
        this.id = id;
        this.image = TilesheetManager.getInstance().getTilesheet(tilesheet).getTile(new Point(x - 1, y - 1));
        this.layer = layer;
    }

    Tile() {
        this.id = "E";
    }

    public static Tile byID(String str) {
        for (Tile t : Tile.values()) {
            if (t.getID().equalsIgnoreCase(str)) return t;
        }

        return null;
    }

    String getID() {
        return id;
    }

    public Layer getLayer() {
        return layer;
    }

    private ImageIcon getImage() {
        if (this.id.equals("E")) JOptionPane.showMessageDialog(null, "Attempting to get image on empty tile!");
        return image;
    }

    public ImageIcon getImage(int width, int height) {
        return UtilityProvider.getResourceUtil().resizeImage(getImage(), width, height);
    }
}