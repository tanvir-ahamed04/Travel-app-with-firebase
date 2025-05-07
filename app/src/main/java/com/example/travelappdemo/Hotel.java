package com.example.travelappdemo;

public class Hotel {
    private String name;
    private String description;
    private double rating;


    public Hotel() {
        // Default constructor required for calls to DataSnapshot.getValue(Hotel.class)
    }

    public Hotel(String name, String description, double rating, String imageUrl) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getRating() { return rating; }


    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setRating(double rating) { this.rating = rating; }

}
