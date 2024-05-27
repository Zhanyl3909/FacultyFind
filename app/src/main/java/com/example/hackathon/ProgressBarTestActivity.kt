package com.example.hackathon

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ProgressBarTestActivity : AppCompatActivity() {
    private var mProgress: ProgressBar? = null
    private var mProgressStatus = 0
    var i: Int = 0

    lateinit var backButton: RelativeLayout // 회원가입 버튼
    lateinit var nextButton: RelativeLayout // 회원가입 버튼
    var radioGroup = findViewById<RadioGroup>(R.id.options)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

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



        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            nextButton.isEnabled = checkedId != -1 // 라디오 버튼이 선택되면 버튼 활성화
            nextButton.setBackgroundResource(R.drawable.main_screen_button)
        }




    }

    companion object {
        private const val PROGRESS = 0x1
    }
}
