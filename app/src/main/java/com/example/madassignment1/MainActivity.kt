package com.example.madassignment1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Add or replace the Statistics fragment
        if (savedInstanceState == null) {
            val fragment = Statistics.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.statistics, fragment).commit()
        }
    }
}