package com.example.madassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartGame extends AppCompatActivity {

    private Button btn6x5;
    private Button btn7x6;
    private Button btn8x7;
    private Button btnStart;

    private String chosenGrid;
    private String player1Name, player2Name;
    private int chosenAvatar1, chosenAvatar2;
    private int chosenColour1, chosenColour2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);

        // Get player profile details from PlayerProfile
        Intent intent = getIntent();
        player1Name = intent.getStringExtra("PLAYER_NAME1");
        chosenAvatar1 = intent.getIntExtra("PLAYER_AVATAR1", R.drawable.a1); // Setting the avatar to default if none is selected
        chosenColour1 = intent.getIntExtra("PLAYER_COLOUR1", R.drawable.red);   // Setting the colour to default if none is selected

        player2Name = intent.getStringExtra("PLAYER_NAME2");
        chosenAvatar2 = intent.getIntExtra("PLAYER_AVATAR2", R.drawable.a2); // Setting the avatar to default if none is selected
        chosenColour2 = intent.getIntExtra("PLAYER_COLOUR2", R.drawable.blue);   // Setting the colour to default if none is selected

        // Initialize buttons
        btn6x5 = findViewById(R.id.btn6x5);
        btn7x6 = findViewById(R.id.btn7x6);
        btn8x7 = findViewById(R.id.btn8x7);
        btnStart = findViewById(R.id.btnStart);

        // Disable START button until a grid is chosen
        btnStart.setEnabled(false);

        // Set click listeners for grid buttons
        btn6x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenGrid = "6x5"; // To set the chosen grid size
                btnStart.setEnabled(true); // Let the Start button to be chosen
            }
        });

        btn7x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenGrid = "7x6"; // To set the chosen grid size
                btnStart.setEnabled(true); // Let the Start button to be chosen
            }
        });

        btn8x7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosenGrid = "8x7"; // To set the chosen grid size
                btnStart.setEnabled(true); // Let the Start button to be chosen
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridActivity(); // Move to the grid activity
            }
        });
    }

    private void gridActivity() {
        Intent boardIntent = null;

        // Determine which grid to open based on the grid user choses
        if ("6x5".equals(chosenGrid)) {
            boardIntent = new Intent(this, Board6x5.class);
        } else if ("7x6".equals(chosenGrid)) {
            boardIntent = new Intent(this, Board7x6.class);
        } else if ("8x7".equals(chosenGrid)) {
            boardIntent = new Intent(this, Board8x7.class);
        }

        if (boardIntent != null) {
            boardIntent.putExtra("PLAYER_NAME1", player1Name);
            boardIntent.putExtra("PLAYER_AVATAR1", chosenAvatar1);
            boardIntent.putExtra("PLAYER_COLOUR1", chosenColour1);

            boardIntent.putExtra("PLAYER_NAME2", player2Name);
            boardIntent.putExtra("PLAYER_AVATAR2", chosenAvatar2);
            boardIntent.putExtra("PLAYER_COLOUR2", chosenColour2);
            startActivity(boardIntent);
        }
    }
}
