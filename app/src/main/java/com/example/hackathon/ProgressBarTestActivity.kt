package com.example.hackathon

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class ProgressBarTestActivity : AppCompatActivity() {
    private var mProgress: ProgressBar? = null
    private var mProgressStatus = 0
    var i: Int = 0

    private lateinit var backButton: RelativeLayout // 회원가입 버튼
    private lateinit var nextButton: RelativeLayout // 회원가입 버튼


    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.main_screen) //check this

        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)

        mProgress = findViewById<View>(R.id.main_screen_progress_bar) as ProgressBar

        Thread {
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
                mProgressStatus = i++

                mProgress!!.post { mProgress!!.progress = mProgressStatus }
            }
        }.start()
    }

    companion object {
        private const val PROGRESS = 0x1
    }
}
