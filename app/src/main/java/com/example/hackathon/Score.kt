package com.example.hackathon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Score : Fragment(R.layout.main_screen) {

    var majorScore: Int = 0
    public fun addTheScore(radiobuttonID: Int) {
        if (radiobuttonID == R.id.option1) {
            majorScore += 2
        } else if (radiobuttonID == R.id.option2) {
            majorScore += 1
        } else {
            majorScore += 0
        }
    }
}