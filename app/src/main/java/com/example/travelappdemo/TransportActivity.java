package com.example.travelappdemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TransportActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        setupToolbar();
        setupNavigationDrawer();
        setupTransportButtons();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
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

    private void setupTransportButtons() {
        Button btnOpenDidi = findViewById(R.id.btn_open_didi);

        btnOpenDidi.setOnClickListener(v -> {
            if (isAppInstalled()) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.sdu.didi.psnger");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            } else {
                Toast.makeText(this, "DiDi app not installed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.sdu.didi.psnger")));
            }
        });
    }

    private boolean isAppInstalled() {
        try {
            getPackageManager().getPackageInfo("com.sdu.didi.psnger", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void openHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void openBestDestinations() {
        startActivity(new Intent(this, BestDestinationsActivity.class));
        finish();
    }
    private void openTransport() {
        startActivity(new Intent(TransportActivity.this, TransportActivity.class));
    }

    private void openHotels() {
        startActivity(new Intent(this, HotelsActivity.class));
        finish();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TransportActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }
}
