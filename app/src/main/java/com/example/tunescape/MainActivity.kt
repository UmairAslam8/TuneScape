package com.example.tunescape

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TuneScape)
        //setContentView(R.layout.activity_player)
        setContentView(R.layout.activity_main)

    }
}