// User.java
package com.example.travelappdemo;

public class User {
    public String username;
    public String email;
    public boolean isAdmin;

    public String uid;

    public User() {} // Needed for Firebase

    public User(String username, String email, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}