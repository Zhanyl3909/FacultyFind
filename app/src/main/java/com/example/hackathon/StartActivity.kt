package com.example.hackathon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.HomeActivity
import com.example.hackathon.R

class StartActivity : AppCompatActivity() {

    private lateinit var startButton: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)

        startButton = findViewById(R.id.start_button)

        // Set click listener for start button
        startButton.setOnClickListener {
            val intent = Intent(this@StartActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onClickStart(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
