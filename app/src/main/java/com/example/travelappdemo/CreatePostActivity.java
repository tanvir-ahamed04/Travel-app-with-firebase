package com.example.travelappdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePostActivity extends AppCompatActivity {

    EditText etTitle, etDescription;
    Button btnCreatePost;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnCreatePost = findViewById(R.id.btnCreatePost);

        dbRef = FirebaseDatabase.getInstance().getReference("posts");

        btnCreatePost.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (!title.isEmpty() && !description.isEmpty()) {
                String key = dbRef.push().getKey();
                Post post = new Post(title, description);
                dbRef.child(key).setValue(post)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(this, "Post Created", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Failed to create post", Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
