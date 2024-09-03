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
        val intent = Intent(this, Board8x7::class.java)
        startActivity(intent)

        // Add or replace the Statistics fragment
//        if (savedInstanceState == null) {
//            val statisticFragment = Statistics.newInstance()
//            supportFragmentManager.beginTransaction().replace(R.id.statistics, statisticFragment).commit()

        // Show which player has won
//            val winnerFragment = Winner.newInstance("name1", "name2")
//            supportFragmentManager.beginTransaction().replace(R.id.winner, winnerFragment).commit()

        // Show no moves left and its a draw if no one wins
//            val drawFragment = Draw.newInstance("name1", "name2")
//            supportFragmentManager.beginTransaction().replace(R.id.draw, drawFragment).commit()
//        }
    }
}