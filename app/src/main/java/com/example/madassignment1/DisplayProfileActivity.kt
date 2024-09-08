package com.example.madassignment1

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DisplayProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_profile)

        // Initialize the views
        val playerNameTextView: TextView = findViewById(R.id.displayPlayerName)
        val avatarImageView: ImageView = findViewById(R.id.displayAvatar)
        val colorPreviewView: View = findViewById(R.id.colorPreview)

        // Retrieve the saved player profile data
        val sharedPref = getSharedPreferences("PlayerProfile", MODE_PRIVATE)
        val playerName = sharedPref.getString("playerName", "No Name")
        val selectedAvatar = sharedPref.getInt("selectedAvatar", -1) // Use -1 to indicate no avatar selected
        val selectedColor = sharedPref.getInt("selectedColor", -1) // Use -1 to indicate no color selected

        // Set the retrieved data to the views
        playerNameTextView.text = "Player Name: $playerName"

        if (selectedAvatar != -1) {
            // If an avatar was selected, set the image resource
            avatarImageView.setImageResource(selectedAvatar)
        } else {
            // Display a placeholder or empty state for the avatar
            avatarImageView.setImageResource(R.drawable.placeholder_avatar) // Use a placeholder image
        }

        if (selectedColor != -1) {
            // If a color was selected, set the background color
            colorPreviewView.setBackgroundResource(selectedColor)
        } else {
            // Display a default color or neutral background
            colorPreviewView.setBackgroundResource(R.color.neutral_color) // Use a neutral color
        }
    }
}
