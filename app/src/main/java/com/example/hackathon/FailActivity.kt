package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.hackathon.R

class FailActivity : AppCompatActivity() {

    private lateinit var againButton: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fail_screen)

        againButton = findViewById(R.id.return_button_1)

        againButton.setOnClickListener {
            val intent = Intent(this@FailActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}