package me.nrubin29.socialgod.audio;

public enum Music {

    TEST("test");

    private final String path;

    Music(String name) {
        this.path = "audio/music/" + name + ".mp3";
    }

    public String getPath() {
        return path;
    }
}