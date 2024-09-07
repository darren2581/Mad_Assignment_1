package com.example.madassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerProfile1 extends AppCompatActivity {

   private String player1Name;
   private int chosenAvatar1; // Store avatar
   private int chosenColour1; // Store colour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerprofile1);

        EditText p1nameInput = findViewById(R.id.player1_enter_name);

        Button btnAvatar1 = findViewById(R.id.choose_avatar1);
        Button btnAvatar2 = findViewById(R.id.choose_avatar2);
        Button btnAvatar3 = findViewById(R.id.choose_avatar3);
        Button btnAvatar4 = findViewById(R.id.choose_avatar4);
        Button btnAvatar5 = findViewById(R.id.choose_avatar5);
        Button btnAvatar6 = findViewById(R.id.choose_avatar6);

        Button btnRed = findViewById(R.id.choose_colour_red);
        Button btnBlue = findViewById(R.id.choose_colour_blue);
        Button btnYellow = findViewById(R.id.choose_colour_yellow);
        Button btnPurple = findViewById(R.id.choose_colour_purple);
        Button btnGrey = findViewById(R.id.choose_colour_grey);
        Button btnBrown = findViewById(R.id.choose_colour_brown);

        Button btnDone = findViewById(R.id.btnDone);

        // Handle avatar selection
        btnAvatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a1; // Set the avatar choice
            }
        });

        btnAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a2; // Set the avatar choice
            }
        });

        btnAvatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a3; // Set the avatar choice
            }
        });

        btnAvatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a4; // Set the avatar choice
            }
        });

        btnAvatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a5; // Set the avatar choice
            }
        });

        btnAvatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAvatar1 = R.drawable.a6; // Set the avatar choice
            }
        });

        // Handle color selection
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.red; // Set the color choice
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.yellow; // Set the color choice
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.blue; // Set the color choice
            }
        });

        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.purple; // Set the color choice
            }
        });

        btnGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.grey; // Set the color choice
            }
        });

        btnBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenColour1 = R.drawable.brown; // Set the color choice
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Name = p1nameInput.getText().toString();

                // Create an Intent to start the StartGame activity
                Intent intent = new Intent(PlayerProfile1.this, PlayerProfile2.class);
                intent.putExtra("PLAYER_NAME1", player1Name);
                intent.putExtra("PLAYER_AVATAR1", chosenAvatar1);
                intent.putExtra("PLAYER_COLOUR1", chosenColour1);
                startActivity(intent); //Move to the StartGame activity
            }
        });
    }
}
