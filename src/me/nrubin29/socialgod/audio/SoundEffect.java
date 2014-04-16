package me.nrubin29.socialgod.audio;

public enum SoundEffect {

    BUMP("bump"),
    CLICK("click");

    private final String path;

    SoundEffect(String name) {
        this.path = "audio/sfx/" + name + ".mp3";
    }

    public String getPath() {
        return path;
    }
}