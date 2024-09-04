package com.example.madassignment1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Board6x5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_board6x5)
        // Retrieve the integer value from the Intent on previous activity
        val played = intent.getIntExtra("PLAYED", -1)
        val player1Name = intent.getStringExtra("PLAYER1_NAME")
        val player2Name = intent.getStringExtra("PLAYER2_NAME")
        val player1Win = intent.getIntExtra("PLAYER1_WIN", 0)
        val player2Win = intent.getIntExtra("PLAYER2_WIN", 0)
        val player1Lose = intent.getIntExtra("PLAYER1_LOSE", 0)
        val player2Lose = intent.getIntExtra("PLAYER2_LOSE", 0)
        val player1Avatar = intent.getStringExtra("PLAYER1_AVATAR")
        val player2Avatar = intent.getStringExtra("PLAYER2_AVATAR")

        if (savedInstanceState == null) {
            val statisticFragment = Statistics.newInstance()
            val args = Bundle()
            args.putInt("PLAYED", played)
            args.putString("PLAYER1_NAME", player1Name)
            args.putString("PLAYER2_NAME", player2Name)
            args.putInt("PLAYER1_WIN", player1Win)
            args.putInt("PLAYER2_WIN", player2Win)
            args.putInt("PLAYER1_LOSE", player1Lose)
            args.putInt("PLAYER2_LOSE", player2Lose)
            args.putString("PLAYER1_AVATAR", player1Avatar)
            args.putString("PLAYER2_AVATAR", player2Avatar)
            statisticFragment.arguments = args

            supportFragmentManager.beginTransaction().replace(R.id.statistics, statisticFragment).commit()
        }
    }
}