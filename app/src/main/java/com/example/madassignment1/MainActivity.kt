package com.example.madassignment1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val intent = Intent(this, Board8x7::class.java).apply {
            putExtra("PLAYED", 10)
            putExtra("PLAYER1_NAME", "Alice")
            putExtra("PLAYER2_NAME", "Bob")
            putExtra("PLAYER1_WIN", 10)
            putExtra("PLAYER2_WIN", 30)
            putExtra("PLAYER1_LOSE", 3)
            putExtra("PLAYER2_LOSE", 7)
            putExtra("PLAYER1_AVATAR", "a1")
            putExtra("PLAYER2_AVATAR", "a4")
        }
        startActivity(intent)
    }
}