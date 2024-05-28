package com.example.hackathon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class ResultActivity : AppCompatActivity() {

    private lateinit var resultMajor1: TextView
    private lateinit var resultMajor2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.last_screen)

        val europeScore = intent.getIntExtra("Europe_KEY", Int.MIN_VALUE)
        val asiaScore = intent.getIntExtra("Asia_KEY", Int.MIN_VALUE)
        val universityScore = intent.getIntExtra("University_KEY", Int.MIN_VALUE)
        val itScore = intent.getIntExtra("IT_KEY", Int.MIN_VALUE)
        val digitalScore = intent.getIntExtra("Digital_KEY", Int.MIN_VALUE)








        val scoreMap = mapOf (
            "Europe" to europeScore,
            "Asia" to asiaScore,
            "University" to universityScore,
            "IT" to itScore,
            "Digital" to digitalScore
        )

        val maxScore = scoreMap.values.maxOrNull() ?: Int.MIN_VALUE
        val maxKeys = scoreMap.filterValues { it == maxScore }.keys

        if (maxKeys.size > 2)


        maxKeys.forEach { key ->
            when (key) {
                "Europe" -> println("A")
                "Asia" -> println("B")
                "University" -> println("C")
                "IT" -> println("IT highest score")
                "Digital" -> println("Digital highest score")
            }
        }



















        val numbers = listOf(europeScore, asiaScore, universityScore, itScore, digitalScore)
        var maxNumber = Int.MIN_VALUE

        for (number in numbers) {
            if (number > maxNumber) {
                maxNumber = number
            }
        }
        val maxNumbers = numbers.filter { it == maxNumber }
        println(maxNumbers)





    }

}



