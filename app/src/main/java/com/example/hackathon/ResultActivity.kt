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
        val socialScore = intent.getIntExtra("Social_KEY", Int.MIN_VALUE)

        println(europeScore)
        println(asiaScore)
        println(universityScore)
        println(itScore)
        println(digitalScore)

        resultMajor1 = findViewById(R.id.result_1)
        resultMajor2 = findViewById(R.id.result_2)

        val scoreMap = mapOf (
            "Europe" to europeScore,
            "Asia" to asiaScore,
            "University" to universityScore,
            "IT" to itScore,
            "Digital" to digitalScore,
            "Social" to socialScore
        )

        val valueKeys = scoreMap.filterValues { it >= 5 }.keys

        println("가장 큰 값을 가진 키: $valueKeys")
        println(valueKeys)

        if (valueKeys.size >= 3 || valueKeys.isEmpty()) {
            setContentView(R.layout.details_screen)
        }

        if (valueKeys.size == 1) {
            valueKeys.forEach { key ->
                when (key) {
                    "Europe" -> resultMajor1.text = "유럽미주 대학"
                    "Asia" -> resultMajor1.text = "아시아 대학"
                    "University" -> resultMajor1.text = "상경 대학"
                    "IT" -> resultMajor1.text = "IT 대학"
                    "Digital" -> resultMajor1.text = "디지털미디어 대학"
                    "Social" -> resultMajor1.text = "사회과학 대학"
                }
            }
        }

        if (valueKeys.size == 2) {
            val maxKey = valueKeys.maxByOrNull { key -> scoreMap[key] ?: Int.MIN_VALUE }
            if (maxKey == "Europe")
                resultMajor1.text = "유럽미주 대학"
            else if (maxKey == "Asia")
                resultMajor1.text = "아시아 대학"
            else if (maxKey == "University")
                resultMajor1.text = "상경 대학"
            else if (maxKey == "IT")
                resultMajor1.text = "IT 대학"
            else if (maxKey == "Digital")
                resultMajor1.text = "디지털미디어 대학"
            else
                resultMajor1.text = "사회과학 대학"

            val remainingKeys = valueKeys.toMutableSet() // 가변적인 집합으로 변환
            maxKey?.let { remainingKeys.remove(it) }

            println("가장 큰 값을 가진 키: $maxKey")
            println("남은 키들: $remainingKeys")

            if (remainingKeys.first() == "Europe")
                resultMajor2.text = "유럽미주 대학"
            else if (remainingKeys.first() == "Asia")
                resultMajor2.text = "아시아 대학"
            else if (remainingKeys.first() == "University")
                resultMajor2.text = "상경 대학"
            else if (remainingKeys.first() == "IT")
                resultMajor2.text = "IT 대학"
            else if (remainingKeys.first() == "Digital")
                resultMajor2.text = "디지털미디어 대학"
            else
                resultMajor1.text = "사회과학 대학"
        }











    }

}



