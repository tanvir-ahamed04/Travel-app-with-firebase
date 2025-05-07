package com.example.travelappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    Button btnCreatePost, btnManageUsers, btnManagePosts, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        // Initialize buttons
        btnCreatePost = findViewById(R.id.btnCreatePost);
        btnManageUsers = findViewById(R.id.btnManageUsers);
        btnManagePosts = findViewById(R.id.btnManagePosts);
        btnLogout = findViewById(R.id.btnLogout);

        // Create Post button
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Create Post page (where admin can create a post)
                startActivity(new Intent(AdminHomeActivity.this, CreatePostActivity.class));
            }
        });

        // Manage Users button
        btnManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Manage Users page
                startActivity(new Intent(AdminHomeActivity.this, ManageUsersActivity.class));
            }
        });

        // Manage Posts button
        btnManagePosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Manage Posts page
                startActivity(new Intent(AdminHomeActivity.this, ManagePostsActivity.class));
            }
        });

        // Logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut(); // Sign out from Firebase
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
                startActivity(intent);
                finish();
            }
        });
    }
}
