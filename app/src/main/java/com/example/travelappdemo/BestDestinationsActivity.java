package com.example.travelappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BestDestinationsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private RecyclerView recyclerView;
    private ArrayList<Post> postList = new ArrayList<>();
    private TextView tvCurrencyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_destination);

        setupToolbar();
        setupNavigationDrawer();
        setupRecyclerView();
        fetchPostsFromFirebase();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    private void setupNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                openHome();
            } else if (itemId == R.id.nav_best_destination) {
                openBestDestinations();
            } else if (itemId == R.id.nav_hotels) {
                openHotels();
            } else if (itemId == R.id.nav_transport) {
                openTransport();
            } else if (itemId == R.id.nav_logout) {
                logout();
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void openHome() {
        startActivity(new Intent(BestDestinationsActivity.this, HomeActivity.class));
    }

    private void openBestDestinations() {
        startActivity(new Intent(BestDestinationsActivity.this, BestDestinationsActivity.class));
    }

    private void openHotels() {
        startActivity(new Intent(BestDestinationsActivity.this, HotelsActivity.class));
    }

    private void openTransport() {
        startActivity(new Intent(BestDestinationsActivity.this, TransportActivity.class));
    }



    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(BestDestinationsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }



    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_best_destinations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchPostsFromFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot postSnap : snapshot.getChildren()) {
                    Post post = postSnap.getValue(Post.class);
                    if (post != null) {
                        postList.add(post);
                    }
                }
                recyclerView.setAdapter(new PostAdapter(BestDestinationsActivity.this, postList)); // fixed: explicitly pass isAdmin = false
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                tvCurrencyResult.setText("Failed to load data.");
            }
        });
    }
}