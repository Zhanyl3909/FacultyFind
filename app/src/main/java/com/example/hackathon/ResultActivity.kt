package com.example.hackathon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var resultMajor1: TextView
    private lateinit var resultMajor2: TextView

    private lateinit var goToDetails: RelativeLayout
    private lateinit var goToFirst: RelativeLayout
    private lateinit var secondMajorResult: LinearLayout

    private lateinit var detailText1: String
    private lateinit var detailText2: String

    @SuppressLint("MissingInflatedId")
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
        println(socialScore)

        resultMajor1 = findViewById(R.id.result_1)
        resultMajor2 = findViewById(R.id.result_2)

        goToFirst = findViewById(R.id.return_button)
        goToDetails = findViewById(R.id.details_view_button)
        secondMajorResult = findViewById(R.id.second_major_result)

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
            val intent = Intent(this@ResultActivity, FailActivity::class.java)
            startActivity(intent)
        }

        if (valueKeys.size == 1) {
            secondMajorResult.visibility = View.GONE;
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
            detailText1 = resultMajor1.text.toString()
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

            detailText1 = resultMajor1.text.toString()
            detailText2 = resultMajor2.text.toString()
        }


        goToDetails.setOnClickListener {
            val intent = Intent(this@ResultActivity, DetailsActivity::class.java)

            if (valueKeys.size == 2) {
                intent.putExtra("Major_Count", 2)
                intent.putExtra("DETAIL_TEXT_title2", detailText2)

                val detailText2 = getDetailTextForResult(resultMajor2.text.toString())
                intent.putExtra("DETAIL_TEXT2", detailText2)

                val detailTextMajor2 = getDetailTextForResultMajor(resultMajor2.text.toString())
                intent.putExtra("DETAIL_TEXT_Major2", detailTextMajor2)

            } else if (valueKeys.size == 1) {
                intent.putExtra("Major_Count", 1)
            }

            intent.putExtra("DETAIL_TEXT_title1", detailText1)

            val detailText1 = getDetailTextForResult(resultMajor1.text.toString())
            intent.putExtra("DETAIL_TEXT1", detailText1)

            val detailTextMajor1 = getDetailTextForResultMajor(resultMajor1.text.toString())
            intent.putExtra("DETAIL_TEXT_Major1", detailTextMajor1)

            startActivity(intent)
        }

        goToFirst.setOnClickListener {
            val intent = Intent(this@ResultActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getDetailTextForResult (result: String): String {
        return when (result) {
            "IT 대학" -> getString(R.string.details_for_IT)
            "디지털미디어 대학" -> getString(R.string.details_for_digitalMedia)
            "사회과학 대학" -> getString(R.string.details_for_society)
            "상경 대학" -> getString(R.string.details_for_sang)
            "아시아 대학" -> getString(R.string.details_for_asia)
            "유럽미주 대학" -> getString(R.string.details_for_europe)
            else -> ""
        }
    }

    private fun getDetailTextForResultMajor (result: String): String {
        return when (result) {
            "IT 대학" -> getString(R.string.details_for_IT_Major)
            "디지털미디어 대학" -> getString(R.string.details_for_digitalMedia_Major)
            "사회과학 대학" -> getString(R.string.details_for_society_Major)
            "상경 대학" -> getString(R.string.details_for_sang_Major)
            "아시아 대학" -> getString(R.string.details_for_asia_Major)
            "유럽미주 대학" -> getString(R.string.details_for_europe_Major)
            else -> ""
        }
    }




}



