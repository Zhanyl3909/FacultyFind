package com.example.hackathon

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_screen)


        val detailText = intent.getStringExtra("DETAIL_TEXT")
        val textViewForDetails = findViewById<TextView>(R.id.textView_forDetails)
        textViewForDetails.text = detailText
    }



}
