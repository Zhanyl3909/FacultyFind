package com.example.hackathon

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {


    private lateinit var start_button: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)


        start_button = findViewById(R.id.start_button)


    }
    fun onClickStart(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    start_button.setOnClickListener {

    }


}

