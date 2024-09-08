package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayerProfile2 : AppCompatActivity() {

    private var selectedAvatar: Int = -1
    private var selectedColor: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile2) // Updated to activity_player_profile2

        val playerNameEditText: EditText = findViewById(R.id.player_enter_name)
        val saveButton: Button = findViewById(R.id.savebutton)

        val avatar1Button: Button = findViewById(R.id.choose_avatar1)
        val avatar2Button: Button = findViewById(R.id.choose_avatar2)
        val avatar3Button: Button = findViewById(R.id.choose_avatar3)
        val avatar4Button: Button = findViewById(R.id.choose_avatar4)
        val avatar5Button: Button = findViewById(R.id.choose_avatar5)
        val avatar6Button: Button = findViewById(R.id.choose_avatar6)

        // (Repeat for other avatars)

        // Color buttons
        val redColorButton: Button = findViewById(R.id.choose_colour_red)
        val blueColorButton: Button = findViewById(R.id.choose_colour_blue)
        val brownColorButton: Button = findViewById(R.id.choose_colour_brown)
        val greyColorButton: Button = findViewById(R.id.choose_colour_grey)
        val purpleColorButton: Button = findViewById(R.id.choose_colour_purple)
        val yellowColorButton: Button = findViewById(R.id.choose_colour_yellow)
        // (Repeat for other colors)
        //(Repeat for other colors)

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
        // (Repeat for other avatars)

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
        // (Repeat for other colors)

        saveButton.setOnClickListener {
            val playerName2 = playerNameEditText.text.toString()

            if (playerName2.isEmpty() || selectedAvatar == -1 || selectedColor == -1) {
                Toast.makeText(this, "Please complete your profile.", Toast.LENGTH_SHORT).show()
            } else {
                savePlayerProfile(playerName2, selectedAvatar, selectedColor)
                Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show()

                // Navigate to another activity, or perform another action
                val intent = Intent(this, PlayerProfile1::class.java)
                intent.putExtra("PLAYER_NAME2", playerName2)
                intent.putExtra("PLAYER_AVATAR2", selectedAvatar)
                intent.putExtra("PLAYER_COLOUR2", selectedColor)
                startActivity(intent)
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