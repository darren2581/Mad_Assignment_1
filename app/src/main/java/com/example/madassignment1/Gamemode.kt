package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Gamemode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gamemode)
        // Find the buttons by their IDs
        val btnPlayAI: Button = findViewById(R.id.btnPlayAI)
        val btnPlayHuman: Button = findViewById(R.id.btnTwoPlayer)

        // Set an OnClickListener for the "Play AI" button
        btnPlayAI.setOnClickListener {
            // Create an Intent to start PlayerProfileActivity
            val intent = Intent(this, PlayerProfile1::class.java)
            startActivity(intent)
        }

        // Set an OnClickListener for the "2 Player" button
        btnPlayHuman.setOnClickListener {
            // Create an Intent to start PlayerProfileActivity
            val intent1 = Intent(this, PlayerProfile1::class.java)
            startActivity(intent1)
            val intent2 = Intent(this, PlayerProfile2::class.java)
            startActivity(intent2)

        }
    }
}