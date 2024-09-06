package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gamemode) // Loads mainpage.xml

        // Find the button by its ID
        val btnPlayAI: Button = findViewById(R.id.btnPlayAI)

        // Set an OnClickListener for the button
        btnPlayAI.setOnClickListener {
            // Create an Intent to start PlayerProfileActivity
            val intent = Intent(this, PlayerProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
