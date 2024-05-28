package com.example.hackathon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

class Score : AppCompatActivity(){

    var major: Int = 0
    var whatSelectRadioInt: Int = 0
    public fun addTheScore() {
        if (whatSelectRadioInt == 2) {
            major += 2
        } else if (whatSelectRadioInt == 1) {
            major += 1
        } else {
            major += 0
        }
    }
}