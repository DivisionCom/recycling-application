package com.example.recycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener{
                val goMenu = Intent(this, MenuActivity::class.java)
                startActivity(goMenu)
        }
    }
}