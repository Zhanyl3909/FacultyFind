package com.example.hackathon

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class ProgressBarTestActivity : AppCompatActivity() {
    private lateinit var mProgress: ProgressBar
    private var mProgressStatus = 0
    private var i: Int = 0

    private lateinit var backButton: RelativeLayout
    private lateinit var nextButton: RelativeLayout
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        radioGroup = findViewById(R.id.options)
        mProgress = findViewById(R.id.main_screen_progress_bar)

        nextButton.isEnabled = false // Initially disable the next button

        // Start a thread to update the progress bar
        Thread {
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                mProgressStatus = i++

                mProgress.post {
                    mProgress.progress = mProgressStatus
                    if (mProgressStatus >= 100) {
                        mProgress.isVisible = false // Hide the progress bar when done
                    }
                }
            }
        }.start()

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            nextButton.isEnabled = checkedId != -1 // Enable the next button if a radio button is selected
            if (checkedId != -1) {
                nextButton.setBackgroundResource(R.drawable.custom_radio_checked)
            } else {
                nextButton.setBackgroundResource(R.drawable.custom_radio_unchecked)
            }
        }
    }
}
