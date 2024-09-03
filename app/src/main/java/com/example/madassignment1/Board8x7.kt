package com.example.madassignment1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Board8x7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_board8x7)
        if (savedInstanceState == null) {
            val statisticFragment = Statistics.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.statistics, statisticFragment).commit()
        }
    }
}