package me.nrubin29.socialgod.network;

import me.nrubin29.socialgod.misc.ColorScheme;

import java.util.ArrayList;

public class Network {

    private final String name;
    private final ColorScheme colorScheme;
    private final ArrayList<Post> posts;

    protected Network(String name, ColorScheme colorScheme) {
        this.name = name;
        this.colorScheme = colorScheme;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public Post[] getPosts() {
        return posts.toArray(new Post[posts.size()]);
    }
}