package com.example.travelappdemo;

public class Post {
    public String title;
    public String description;
    public String key; // Needed for deletion

    public Post() {}

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
