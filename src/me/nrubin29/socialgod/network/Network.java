package me.nrubin29.socialgod.network;

import java.util.ArrayList;

public class Network {

    private final String name;
    private final ArrayList<Post> posts;

    protected Network(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Post[] getPosts() {
        return posts.toArray(new Post[posts.size()]);
    }
}