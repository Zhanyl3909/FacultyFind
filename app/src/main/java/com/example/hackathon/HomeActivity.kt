

package com.example.hackathon

import android.os.Bundle
import android.view.View
import android.widget.Button
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
    private lateinit var questionTextNumber: TextView

    private var currentQuestionIndex = 0
    private val questions = listOf (
        "다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",
        "유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",
        "외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)


        //initialize button through id in the main.xml
        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        radioGroup = findViewById(R.id.options)
        mProgress = findViewById(R.id.main_screen_progress_bar)

        questionTextView = findViewById(R.id.questionTextView)
        questionTextNumber = findViewById(R.id.questions_no)

        setQuestion()

        // Start a thread to update the progress bar
        Thread {
            while (mProgressStatus < 20) {
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
            nextButton.isEnabled =
                checkedId != -1 // Enable the next button if a radio button is selected
            nextButton.setBackgroundResource(R.drawable.main_screen_button)
        }



        //this makes change text on the last question to see the result
        nextButton.setOnClickListener {
            val isSelected = radioGroup.checkedRadioButtonId
            if (isSelected == -1) {
                Toast.makeText(this@HomeActivity, "답변을 선택해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                if (currentQuestionIndex < questions.size - 1) {
                    goToNextQuestion()
                } else {
                    // If it's the last question, change the button text
                    nextButton.findViewById<TextView>(R.id.text_for_nextButton).text = "결과 보기"
                }
            }
        }

        backButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                goToBackQuestion()
            }
        }
    

        // Make the prev button invisible initially
        backButton.visibility = View.INVISIBLE



    }


    private fun setQuestion() {  //text 표시
        questionTextView.text = questions[currentQuestionIndex]
        questionTextNumber.text = "${currentQuestionIndex + 1}."
    }
    private fun goToNextQuestion() {    //바꾸는 것
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        setQuestion()
        // Show the back button when moving to the next question
        backButton.visibility = View.VISIBLE
        updateProgressBar()
    }
    private fun goToBackQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
        if (currentQuestionIndex < 0) {
            currentQuestionIndex += questions.size
        }
        setQuestion()
        updateProgressBar()
    }
    private fun updateProgressBar() {
        val totalQuestions = questions.size
        val progress = ((currentQuestionIndex + 1) / totalQuestions.toDouble() * 100).toInt()
        mProgress.progress = progress
    }

}