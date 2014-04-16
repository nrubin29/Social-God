package me.nrubin29.socialgod.tile;

import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Tilesheet {

    private final String name;
    private final HashMap<Point, ImageIcon> tiles = new HashMap<>();
    private BufferedImage image;

    public Tilesheet(String path, String name) {
        this.name = name.substring(0, name.lastIndexOf("."));

        try {
            image = UtilityProvider.getResourceUtil().getBufferedImage(path + "/" + this.name.toLowerCase());

            int xTiles = image.getWidth() / Constants.DEFAULT_TILE_DIM;
            int yTiles = image.getHeight() / Constants.DEFAULT_TILE_DIM;

            for (int x = 0; x < xTiles; x++) {
                for (int y = 0; y < yTiles; y++) {

                    BufferedImage tileImage = new BufferedImage(Constants.DEFAULT_TILE_DIM, Constants.DEFAULT_TILE_DIM, BufferedImage.TYPE_INT_ARGB);

                    tileImage.setRGB(0, 0, Constants.DEFAULT_TILE_DIM, Constants.DEFAULT_TILE_DIM, image.getRGB(x * Constants.DEFAULT_TILE_DIM, y * Constants.DEFAULT_TILE_DIM, Constants.DEFAULT_TILE_DIM, Constants.DEFAULT_TILE_DIM, null, 0, Constants.DEFAULT_TILE_DIM), 0, Constants.DEFAULT_TILE_DIM);

                    tiles.put(new Point(x, y), new ImageIcon(tileImage));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public ImageIcon getTile(Point p) {
        return tiles.get(p);
    }

    ImageIcon getImage() {
        return new ImageIcon(image);
    }

    public ImageIcon getImage(int width, int height) {
        return UtilityProvider.getResourceUtil().resizeImage(getImage(), width, height);
    }
}