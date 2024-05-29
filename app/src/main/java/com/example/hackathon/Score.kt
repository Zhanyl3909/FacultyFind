package com.example.hackathon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class Score {

    val majorList = arrayListOf(0)

    fun addTheScore(radiobuttonID: Int) {
        val addScore: Int
        when (radiobuttonID) {
            R.id.option1 -> addScore = 2
            R.id.option2 -> addScore = 1
            else -> addScore = 0
        }
        majorList.add(addScore)
    }

    fun minusTheScore() {
        majorList.removeLastOrNull()
    }

    fun sumScore(): Int {
        val sum = majorList.sum()
        return sum
    }



}