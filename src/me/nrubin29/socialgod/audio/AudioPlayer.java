package me.nrubin29.socialgod.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import me.nrubin29.socialgod.util.UtilityProvider;

public class AudioPlayer {

    private static final AudioPlayer instance = new AudioPlayer();

    private AudioPlayer() {

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
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, false);
    }

    public void playSoundEffect(final SoundEffect audio) {
        UtilityProvider.getThreadUtil().runThreadInBackground(() -> {
            try {
                Media hit = new Media(audio.getPath());
                MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
            } catch (Exception ignored) {
            }
        }, false);
    }
}