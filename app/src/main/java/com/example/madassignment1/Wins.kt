package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Wins : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wins)

        // Receive data
        val winnerName = intent.getStringExtra("WINNER_NAME")
        val winnerAvatar = intent.getIntExtra("WINNER_AVATAR", R.drawable.a2)

        // Update the UI
        val winnerTextView: TextView = findViewById(R.id.winner)
        val winnerAvatarImageView: ImageView = findViewById(R.id.winnerAvatar)

        winnerTextView.text = "$winnerName WON!!!"
        winnerAvatarImageView.setImageResource(winnerAvatar)
    }
}
