package com.example.travelappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HotelsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private EditText etSearch;
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList = new ArrayList<>();
    private DatabaseReference hotelsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        setupToolbar();
        setupNavigationDrawer();

        etSearch = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.recyclerview_hotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelAdapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(hotelAdapter);

        hotelsRef = FirebaseDatabase.getInstance().getReference("hotels");

        // Fetch all hotels by default
        fetchAllHotels();

        // Add search functionality
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    searchHotels(query);
                } else {
                    fetchAllHotels(); // Reload all hotels if search query is empty
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
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
        startActivity(new Intent(HotelsActivity.this, HomeActivity.class));
    }

    private void openBestDestinations() {
        startActivity(new Intent(HotelsActivity.this, BestDestinationsActivity.class));
    }

    private void openHotels() {
        startActivity(new Intent(HotelsActivity.this, HotelsActivity.class));
    }

    private void openTransport() {
        startActivity(new Intent(HotelsActivity.this, TransportActivity.class));
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HotelsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Close the current activity
    }

    private void fetchAllHotels() {
        hotelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotelList.clear();
                for (DataSnapshot countrySnap : snapshot.getChildren()) {
                    for (DataSnapshot citySnap : countrySnap.getChildren()) {
                        for (DataSnapshot hotelSnap : citySnap.getChildren()) {
                            Hotel hotel = hotelSnap.getValue(Hotel.class);
                            if (hotel != null) {
                                hotelList.add(hotel);
                            }
                        }
                    }
                }
                hotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HotelsActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchHotels(String query) {
        hotelsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotelList.clear();
                for (DataSnapshot countrySnap : snapshot.getChildren()) {
                    String country = countrySnap.getKey();
                    for (DataSnapshot citySnap : countrySnap.getChildren()) {
                        String city = citySnap.getKey();
                        if (country.toLowerCase().contains(query.toLowerCase()) ||
                                city.toLowerCase().contains(query.toLowerCase())) {
                            for (DataSnapshot hotelSnap : citySnap.getChildren()) {
                                Hotel hotel = hotelSnap.getValue(Hotel.class);
                                if (hotel != null) {
                                    hotelList.add(hotel);
                                }
                            }
                        }
                    }
                }
                hotelAdapter.notifyDataSetChanged();
                if (hotelList.isEmpty()) {
                    Toast.makeText(HotelsActivity.this, "No hotels found for \"" + query + "\"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HotelsActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}