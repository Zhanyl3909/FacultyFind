package com.example.hackathon

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R

class DetailsActivity : AppCompatActivity() {

    lateinit var titleForDetails1: TextView
    lateinit var titleForDetails2: TextView
    lateinit var textViewForDetails: TextView
    lateinit var textViewForDetailsMajor: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_screen)

//        val europeScore = intent.getStringExtra("Europe_String")
//        val asiaScore = intent.getStringExtra("Asia_String")
//        val universityScore = intent.getStringExtra("University_String")
//        val itScore = intent.getStringExtra("IT_String")
//        val digitalScore = intent.getStringExtra("Digital_String")
//        val socialScore = intent.getStringExtra("Social_String")
//
//        val scoreMap = mapOf (
//            "Europe" to europeScore,
//            "Asia" to asiaScore,
//            "University" to universityScore,
//            "IT" to itScore,
//            "Digital" to digitalScore,
//            "Social" to socialScore
//        )

        val majorCount = intent.getStringExtra("Major_Count")

        if (majorCount == "1") {

        }


        titleForDetails1 = findViewById(R.id.details_faculty_text)
        titleForDetails1.text = intent.getStringExtra("DETAIL_TEXT_title1")

        titleForDetails2 = findViewById(R.id.details_faculty_text_second)
        titleForDetails2.text = intent.getStringExtra("DETAIL_TEXT_title2")

        textViewForDetails = findViewById(R.id.textView_forDetails)
        textViewForDetails.text = intent.getStringExtra("DETAIL_TEXT")

        textViewForDetailsMajor = findViewById(R.id.detail_faculty)
        textViewForDetailsMajor.text = intent.getStringExtra("DETAIL_TEXT_Major")
    }



}
