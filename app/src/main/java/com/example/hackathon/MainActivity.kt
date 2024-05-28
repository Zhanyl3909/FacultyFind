package com.example.hackathon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


//Lounge Screen Disappears in 2 seconds (code below)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.welcome_screen)

        val welcomeScreenView = findViewById<View>(R.id.welcome_screen)
        ViewCompat.setOnApplyWindowInsetsListener(welcomeScreenView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Use Handler directly on the main thread
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MainActivity, StartActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
