package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayerProfileActivityTwo : AppCompatActivity() {

    private var selectedAvatar: Int = -1 // -1 indicates no avatar selected
    private var selectedColor: Int = -1 // -1 indicates no color selected

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playerprofiletwo)

        // Initialize the views
        val playerNameEditText: EditText = findViewById(R.id.player_enter_name)
        val saveButton: Button = findViewById(R.id.savebutton)

        // Avatar buttons
        val avatar1Button: Button = findViewById(R.id.choose_avatar1)
        val avatar2Button: Button = findViewById(R.id.choose_avatar2)
        val avatar3Button: Button = findViewById(R.id.choose_avatar3)
        val avatar4Button: Button = findViewById(R.id.choose_avatar4)
        val avatar5Button: Button = findViewById(R.id.choose_avatar5)
        val avatar6Button: Button = findViewById(R.id.choose_avatar6)

        // Color buttons
        val redColorButton: Button = findViewById(R.id.choose_colour_red)
        val blueColorButton: Button = findViewById(R.id.choose_colour_blue)
        val brownColorButton: Button = findViewById(R.id.choose_colour_brown)
        val greyColorButton: Button = findViewById(R.id.choose_colour_grey)
        val purpleColorButton: Button = findViewById(R.id.choose_colour_purple)
        val yellowColorButton: Button = findViewById(R.id.choose_colour_yellow)

        // Set click listeners for avatar buttons
        avatar1Button.setOnClickListener {
            selectedAvatar = R.drawable.a1
        }
        avatar2Button.setOnClickListener {
            selectedAvatar = R.drawable.a2
        }
        avatar3Button.setOnClickListener {
            selectedAvatar = R.drawable.a3
        }
        avatar4Button.setOnClickListener {
            selectedAvatar = R.drawable.a4
        }
        avatar5Button.setOnClickListener {
            selectedAvatar = R.drawable.a5
        }
        avatar6Button.setOnClickListener {
            selectedAvatar = R.drawable.a6
        }

        // Set click listeners for color buttons
        redColorButton.setOnClickListener {
            selectedColor = R.drawable.red
        }
        blueColorButton.setOnClickListener {
            selectedColor = R.drawable.blue
        }
        greyColorButton.setOnClickListener {
            selectedColor = R.drawable.grey
        }
        yellowColorButton.setOnClickListener {
            selectedColor = R.drawable.yellow
        }
        purpleColorButton.setOnClickListener {
            selectedColor = R.drawable.purple
        }
        brownColorButton.setOnClickListener {
            selectedColor = R.drawable.brown
        }

        // Save button click listener
        saveButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString()

            if (playerName.isEmpty() || selectedAvatar == -1 || selectedColor == -1) {
                Toast.makeText(this, "Please complete your profile.", Toast.LENGTH_SHORT).show()
            }
            else
            {
                savePlayerProfile(playerName, selectedAvatar, selectedColor)
                Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()

                // Navigate to the next activity
                val intent = Intent(this, PlayerProfileActivity::class.java) // Change this to the correct target activity
                startActivity(intent) //(add the next activity here)
            }
        }
    }

    private fun savePlayerProfile(name: String, avatar: Int, color: Int) {
        val sharedPref = getSharedPreferences("PlayerProfile", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("playerName2", name)
            putInt("selectedAvatar2", avatar)
            putInt("selectedColor2", color)
            apply()
        }
    }
}
