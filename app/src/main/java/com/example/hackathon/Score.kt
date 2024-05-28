package com.example.hackathon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Score {

    var majorScore: Int = 0
    fun addTheScore(radiobuttonID: Int) {
        when (radiobuttonID) {
            R.id.option1 -> majorScore += 2
            R.id.option2 -> majorScore += 1
            else -> majorScore += 0
        }
    }


}