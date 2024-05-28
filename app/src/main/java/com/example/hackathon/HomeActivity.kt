

package com.example.hackathon

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
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

    private lateinit var questionTextView: TextView

    private var currentQuestionIndex = 0
    private val questions = listOf(
        "다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",
        "유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",
        "외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        radioGroup = findViewById(R.id.options)
        mProgress = findViewById(R.id.main_screen_progress_bar)

        questionTextView = findViewById(R.id.questionTextView)

        setQuestion()

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
            val isSelected = radioGroup.checkedRadioButtonId
            if (isSelected == -1) {
                Toast.makeText(this@HomeActivity, "답변을 선택해 주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                goToNextQuestion()
                }
            }

        backButton.setOnClickListener {
            goToBackQuestion()
        }




    }



    private fun setQuestion() {  //text 표시
        questionTextView.text = questions[currentQuestionIndex]
    }
    private fun goToNextQuestion() {    //바꾸는 것
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        setQuestion()
    }
    private fun goToBackQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
        setQuestion()
    }

}