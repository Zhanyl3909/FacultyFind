package com.example.hackathon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R

class DetailsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_screen)

        val europeScore = intent.getStringExtra("Europe_String")
        val asiaScore = intent.getStringExtra("Asia_String")
        val universityScore = intent.getStringExtra("University_String")
        val itScore = intent.getStringExtra("IT_String")
        val digitalScore = intent.getStringExtra("Digital_String")
        val socialScore = intent.getStringExtra("Social_String")

        val scoreMap = mapOf (
            "Europe" to europeScore,
            "Asia" to asiaScore,
            "University" to universityScore,
            "IT" to itScore,
            "Digital" to digitalScore,
            "Social" to socialScore
        )

        val detailText1 = intent.getStringExtra("DETAIL_TEXT1")
        val titleForDetails1 = findViewById<TextView>(R.id.details_faculty_text)
        titleForDetails1.text = detailText1

//        val detailText2 = intent.getStringExtra("DETAIL_TEXT1")
//        val titleForDetails2 = findViewById<TextView>(R.id.details_faculty_text_second)
//        titleForDetails2.text = detailText2

        val detailText = intent.getStringExtra("DETAIL_TEXT")
        val textViewForDetails = findViewById<TextView>(R.id.textView_forDetails)
        textViewForDetails.text = detailText
    }



}
