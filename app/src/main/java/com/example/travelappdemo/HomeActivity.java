package com.example.travelappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Button bestDestinationBtn;
    private Button checkHotelPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();
        setupNavigationDrawer();
        setupViews();
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

    private void setupViews() {
        bestDestinationBtn = findViewById(R.id.bestDestinationBtn);
        bestDestinationBtn.setOnClickListener(v -> openBestDestinations());

        checkHotelPageBtn = findViewById(R.id.checkHotelPageBtn);
        checkHotelPageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HotelsActivity.class);
            startActivity(intent);
        });
    }

    private void openHome() {
        // Already on home screen
    }

    private void openBestDestinations() {
        startActivity(new Intent(HomeActivity.this, BestDestinationsActivity.class));
    }

    private void openHotels() {
        startActivity(new Intent(HomeActivity.this, HotelsActivity.class));
    }

    private void openTransport() {
        startActivity(new Intent(HomeActivity.this, TransportActivity.class));
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut(); // Sign out from Firebase
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }
}