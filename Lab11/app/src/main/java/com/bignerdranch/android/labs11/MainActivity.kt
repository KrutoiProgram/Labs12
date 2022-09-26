package com.bignerdranch.android.labs11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var btnIn : Button
private lateinit var btnOut : Button
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnIn = findViewById(R.id.button)
        btnOut = findViewById(R.id.button2)
        btnIn.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity((intent))
        }
        btnOut.setOnClickListener {
            val intent = Intent(this, OutputActivity::class.java)
            startActivity((intent))
        }

    }
}