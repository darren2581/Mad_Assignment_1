package com.example.madassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // Main layout with a "Start" button

        // Find the "Start" button in the main layout
        Button startProfileButton = findViewById(R.id.startProfile);

        // Set up the listener to handle click events
        startProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PlayerProfile activity when the button is clicked
                Intent intent = new Intent(MainActivity.this, PlayerProfile1.class);
                startActivity(intent);
            }
        });
    }
}
