package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Button;
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

        var boardSize = intent.getStringExtra("BOARD_SIZE")
        var played = intent.getIntExtra("PLAYED", 0)
        var player1Win = intent.getIntExtra("PLAYER1_WIN", 0)
        var player2Win = intent.getIntExtra("PLAYER2_WIN", 0)
        var player1Lose = intent.getIntExtra("PLAYER1_LOSE", 0)
        var player2Lose = intent.getIntExtra("PLAYER2_LOSE", 0)

        // Increment played count
        played += 1

        // Update the UI
        val winnerTextView: TextView = findViewById(R.id.winner)
        val winnerAvatarImageView: ImageView = findViewById(R.id.winnerAvatar)

        winnerTextView.text = "$winnerName WON!!!"
        winnerAvatarImageView.setImageResource(winnerAvatar)

        // Initialize Play Again and Exit buttons
        val playAgainButton: Button = findViewById(R.id.btnPlayAgain)
        val exitButton: Button = findViewById(R.id.btnExit)

        // Set up Play Again button click listener
        playAgainButton.setOnClickListener {
            if (winnerName == "Player 1") {
                player1Win += 1
                player2Lose += 1
            }
            else if (winnerName == "Player 2") {
                player2Win += 1
                player1Lose += 1
            }

            // Redirect to the correct board based on the board size
            val intent = when (boardSize) {
                "6x5" -> Intent(this@Wins, Board6x5a::class.java)
                "7x6" -> Intent(this@Wins, Board7x6a::class.java)
                "8x7" -> Intent(this@Wins, Board8x7a::class.java)
                else -> Intent(this@Wins, Board8x7a::class.java) // Default to 8x7
            }

            // Pass the updated statistics to the board activity
            intent.putExtra("PLAYER1_WIN", player1Win)
            intent.putExtra("PLAYER2_WIN", player2Win)
            intent.putExtra("PLAYER1_LOSE", player1Lose)
            intent.putExtra("PLAYER2_LOSE", player2Lose)
            intent.putExtra("PLAYED", played)
            intent.putExtra("BOARD_SIZE", boardSize)

            startActivity(intent)
            finish() // Close the Wins activity
        }

        // Set up Exit button click listener
        exitButton.setOnClickListener {
            finishAffinity() // Exit the app
        }

    }
}