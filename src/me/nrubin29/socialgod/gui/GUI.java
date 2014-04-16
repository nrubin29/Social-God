package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.audio.AudioPlayer;
import me.nrubin29.socialgod.audio.SoundEffect;
import me.nrubin29.socialgod.entity.Player;
import me.nrubin29.socialgod.event.Event.EventType;
import me.nrubin29.socialgod.keycommand.KeyCommandManager;
import me.nrubin29.socialgod.map.Direction;
import me.nrubin29.socialgod.map.MapManager;
import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.misc.Session;
import me.nrubin29.socialgod.tile.Layer;
import me.nrubin29.socialgod.tile.Location;
import me.nrubin29.socialgod.tile.Row;
import me.nrubin29.socialgod.tile.Tile;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;

public final class GUI extends JPanel {

    public GUI() {
        KeyCommandManager.getInstance().setup(this);

        setLayout(null);
        setPreferredSize(Constants.DIMENSION);

        Timer t = new Timer(1000 / 60, e -> repaint());
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int j = 0; j < Constants.NUM_ROWS; j++) {
            for (int i = 0; i < Constants.TILES_PER_ROW; i++) {
                g.drawImage(MapManager.getInstance().getCurrentMap().getType().getBackgroundTile().getImage(Constants.TILE_WIDTH, Constants.TILE_HEIGHT).getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
            }
        }

        drawTiles(g, Layer.BELOW);
        drawTiles(g, Layer.ON);
        drawTiles(g, Layer.ENTITY);
        drawTiles(g, Layer.ABOVE);
    }

    private void drawTiles(Graphics g, Layer l) {
        for (int j = 0; j < Constants.NUM_ROWS; j++) {
            Row row = MapManager.getInstance().getCurrentMap().getRow(j);
            for (int i = 0; i < Constants.TILES_PER_ROW; i++) {
                Location loc = row.locationAt(i);
                if (l == Layer.ENTITY) {
                    if (loc.getEntity() != null) {
                        g.drawImage(loc.getEntity().getCurrentImage(Constants.TILE_WIDTH, Constants.TILE_HEIGHT).getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
                    }
                } else {
                    if (loc.getTile() != Tile.EMPTY && loc.getTile().getLayer() == l) {
                        g.drawImage(loc.getTile().getImage(Constants.TILE_WIDTH, Constants.TILE_HEIGHT).getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
                    }
                }
            }
        }
    }

    public void movement(final Direction d) {
        Player player = Session.getInstance().getPlayer();
        boolean didMove = false;
        Point to = d.getPointRelativeTo(player.getLocation().getPoint());

        player.setCurrentImage(d, true);

        if (
                to.getX() >= 0 &&
                        to.getX() <= getSize().getWidth() - Constants.TILE_WIDTH &&
                        to.getY() >= 0 &&
                        to.getY() <= getSize().getHeight() - Constants.TILE_HEIGHT &&
                        !(MapManager.getInstance().getCurrentMap().getLocation(to).getTile().getLayer() == Layer.ON) &&
                        MapManager.getInstance().getCurrentMap().getLocation(to).getEntity() == null
                ) {

            player.setLocation(MapManager.getInstance().getCurrentMap().getLocation(to));
            didMove = true;
        }

        UtilityProvider.getThreadUtil().animate(() -> player.setCurrentImage(d, false));

        if (didMove) {
            player.getLocation().checkEvents(EventType.MOVE);
        } else {
            AudioPlayer.getInstance().playSoundEffect(SoundEffect.BUMP);
        }
    }
}