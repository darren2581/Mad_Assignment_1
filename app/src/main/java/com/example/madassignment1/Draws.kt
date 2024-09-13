package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Draws : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_draws)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the board size passed from the previous activity
        val boardSize = intent.getStringExtra("BOARD_SIZE")
        val played = intent.getIntExtra("PLAYED", 0)
        val player1Win = intent.getIntExtra("PLAYER1_WIN", 0)
        val player2Win = intent.getIntExtra("PLAYER2_WIN", 0)

        // Initialize Play Again and Exit buttons
        val playAgainButton: Button = findViewById(R.id.buttonPlayAgain)
        val exitButton: Button = findViewById(R.id.buttonExit)

        // Set up Play Again button click listener
        playAgainButton.setOnClickListener {
            val intent = when (boardSize) {
                "6x5" -> Intent(this@Draws, Board6x5a::class.java)
                "7x6" -> Intent(this@Draws, Board7x6a::class.java)
                "8x7" -> Intent(this@Draws, Board8x7a::class.java)
                else -> Intent(this@Draws, Board8x7a::class.java) // Default to 8x7 if size not recognized
            }

            // Pass the updated stats back to the board activity
            intent.putExtra("PLAYER1_WIN", player1Win)
            intent.putExtra("PLAYER2_WIN", player2Win)
            intent.putExtra("PLAYED", played)

            startActivity(intent)
            finish() // Close the current Draws activity
        }

        // Set up Exit button click listener
        exitButton.setOnClickListener {
            finishAffinity() // Close the app
        }
    }
}