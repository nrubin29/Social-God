package me.nrubin29.socialgod.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import me.nrubin29.socialgod.util.UtilityProvider;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    private static final AudioPlayer instance = new AudioPlayer();
    private final HashMap<MediaPlayer, Double> players;
    private boolean muted = false;

    private AudioPlayer() {
        players = new HashMap<>();
    }

    public static AudioPlayer getInstance() {
        return instance;
    }

    public void setBackgroundMusic(final Music audio) {
        UtilityProvider.getThreadUtil().runThreadInBackground(() -> {
            try {
                Media hit = new Media(UtilityProvider.getResourceUtil().getResource(audio.getPath()).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.setCycleCount(Integer.MAX_VALUE);
                mediaPlayer.setVolume(.5);
                mediaPlayer.play();
                players.put(mediaPlayer, .5);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }, false);
    }

    public void playSoundEffect(final SoundEffect audio) {
        UtilityProvider.getThreadUtil().runThreadInBackground(() -> {
            try {
                Media hit = new Media(UtilityProvider.getResourceUtil().getResource(audio.getPath()).toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.setVolume(2);
                mediaPlayer.play();
                players.put(mediaPlayer, 2D);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }, false);
    }

    public void toggleMute() {
        muted = !muted;

        if (muted) {
            for (Map.Entry<MediaPlayer, Double> player : players.entrySet()) {
                player.getKey().setVolume(0);
            }
        } else {
            for (Map.Entry<MediaPlayer, Double> player : players.entrySet()) {
                player.getKey().setVolume(player.getValue());
            }
        }
    }
}