package me.nrubin29.socialgod.network;

public class Post {

    private final String author;
    private final String contents;
    private int likes;

    public Post(String author, String contents) {
        this.author = author;
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void modifyLikes(int likes) {
        this.likes += likes;
    }
}