

package com.example.hackathon

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class HomeActivity : AppCompatActivity() {
    private lateinit var mProgress: ProgressBar
    private var mProgressStatus = 1
    private var i: Int = 1

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

        // Start a thread to update the progress bar
        Thread {
            while (mProgressStatus < 25) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                mProgressStatus = i++

                mProgress.post {
                    mProgress.progress = mProgressStatus
                }
            }
        }.start()

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            nextButton.isEnabled = checkedId != -1 // Enable the next button if a radio button is selected
            nextButton.setBackgroundResource(R.drawable.main_screen_button)
        }





        nextButton.setOnClickListener {

            val isSelected =  radioGroup.checkedRadioButtonId
            if (isSelected == -1) {
                Toast.makeText(this@HomeActivity, "답변을 선택해 주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@HomeActivity, "다음", Toast.LENGTH_SHORT)
                    .show()
            }
        }




    }
}