package me.nrubin29.socialgod.gui;

import me.nrubin29.socialgod.audio.AudioPlayer;
import me.nrubin29.socialgod.audio.SoundEffect;
import me.nrubin29.socialgod.entity.Player;
import me.nrubin29.socialgod.event.EventDispatcher;
import me.nrubin29.socialgod.event.events.InteractEvent;
import me.nrubin29.socialgod.event.events.InteractWithEntityEvent;
import me.nrubin29.socialgod.event.events.MoveEvent;
import me.nrubin29.socialgod.map.Direction;
import me.nrubin29.socialgod.map.Location;
import me.nrubin29.socialgod.map.MapManager;
import me.nrubin29.socialgod.map.Row;
import me.nrubin29.socialgod.misc.Constants;
import me.nrubin29.socialgod.tile.Layer;
import me.nrubin29.socialgod.tile.Tile;
import me.nrubin29.socialgod.util.UtilityProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public final class GUI extends JPanel {

    private final Player player;
    int keyPressed = -1;
    boolean metaPressed = false;
    private boolean inputEnabled = true;
    private SocialBridge socialBridge;

    public GUI() {
        player = new Player("Player");

        Timer t = new Timer(1000 / 60, e -> tick());
        t.start();
    }

    private void tick() {
        if (inputEnabled && keyPressed != -1) {
            if (keyPressed >= KeyEvent.VK_LEFT && keyPressed <= KeyEvent.VK_DOWN) {
                movement(Direction.valueOf(keyPressed));
            } else if (keyPressed == KeyEvent.VK_ENTER) {
                try {
                    final Direction d = player.getCurrentDirection();
                    final Location location = MapManager.getInstance().getCurrentMap().getLocation(
                            new Point(
                                    player.getLocation().getPoint().x + d.getMovement().x,
                                    player.getLocation().getPoint().y + d.getMovement().y
                            )
                    );

                    if (location.getEntity() != null) {
                        EventDispatcher.getInstance().callEvent(new InteractWithEntityEvent(location, location.getEntity()));
                    } else {
                        EventDispatcher.getInstance().callEvent(new InteractEvent(location));
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
            } else if (keyPressed == KeyEvent.VK_S) {
                if (socialBridge == null) add(socialBridge = new SocialBridge());
                else {
                    remove(socialBridge);
                    socialBridge = null;
                }
            } else if (keyPressed == KeyEvent.VK_M && metaPressed) {
                AudioPlayer.getInstance().toggleMute();
            }

            keyPressed = -1;
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        drawTiles(g, Layer.BACKGROUND);
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
                if (l == Layer.BACKGROUND) {
                    g.drawImage(MapManager.getInstance().getCurrentMap().getType().getBackgroundTile().getImage().getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
                } else if (l == Layer.ENTITY) {
                    if (loc.getEntity() != null) {
                        g.drawImage(loc.getEntity().getCurrentImage().getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
                    }
                } else {
                    if (loc.getTile() != Tile.EMPTY && loc.getTile().getLayer() == l) {
                        g.drawImage(loc.getTile().getImage().getImage(), i * Constants.TILE_WIDTH, j * Constants.TILE_HEIGHT, this);
                    }
                }
            }
        }
    }

    void movement(final Direction d) {
        boolean didMove = false;
        Location from = player.getLocation();
        Location to = MapManager.getInstance().getCurrentMap().getLocation(d.getPointRelativeTo(player.getLocation().getPoint()));

        player.setCurrentImage(d, true);

        if (
                to.getPoint().getX() >= 0 &&
                        to.getPoint().getX() <= getSize().getWidth() - Constants.TILE_WIDTH &&
                        to.getPoint().getY() >= 0 &&
                        to.getPoint().getY() <= getSize().getHeight() - Constants.TILE_HEIGHT &&
                        !(to.getTile().getLayer() == Layer.ON) &&
                        to.getEntity() == null
                ) {

            player.setLocation(to);
            didMove = true;
        }

        UtilityProvider.getThreadUtil().animate(() -> player.setCurrentImage(d, false));

        if (didMove) {
            EventDispatcher.getInstance().callEvent(new MoveEvent(from));
        } else {
            AudioPlayer.getInstance().playSoundEffect(SoundEffect.BUMP);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setInputEnabled(boolean inputEnabled) {
        this.inputEnabled = inputEnabled;
    }
}