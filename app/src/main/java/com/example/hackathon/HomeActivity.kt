

package com.example.hackathon

import android.annotation.SuppressLint
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
    private lateinit var questionTextNumberProgress: TextView

    private var currentQuestionIndex = 0
    private var questions = listOf (
        Pair("다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",""),
        Pair("유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",""),
        Pair("외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다.",""),
        Pair("국제 관계나 다문화 사회에 대해 관심을 가지고, 세계적인 이슈에 대해 논의하는 것을 좋아한다.",""),
        Pair("아시아 문화나 역사에 대한 이해와 관심이 많아서 아시아 국가들을 여행하고 싶은 편이다.",""),
        Pair("다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",""),
        Pair("유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",""),
        Pair("외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다.",""),
        Pair("국제 관계나 다문화 사회에 대해 관심을 가지고, 세계적인 이슈에 대해 논의하는 것을 좋아한다.",""),
        Pair("아시아 문화나 역사에 대한 이해와 관심이 많아서 아시아 국가들을 여행하고 싶은 편이다.",""),
        Pair("다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",""),
        Pair("유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",""),
        Pair("외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다.",""),
        Pair("국제 관계나 다문화 사회에 대해 관심을 가지고, 세계적인 이슈에 대해 논의하는 것을 좋아한다.",""),
        Pair("아시아 문화나 역사에 대한 이해와 관심이 많아서 아시아 국가들을 여행하고 싶은 편이다.",""),
        Pair("다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.",""),
        Pair("유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.",""),
        Pair("외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다.",""),
        Pair("국제 관계나 다문화 사회에 대해 관심을 가지고, 세계적인 이슈에 대해 논의하는 것을 좋아한다.",""),
        Pair("아시아 문화나 역사에 대한 이해와 관심이 많아서 아시아 국가들을 여행하고 싶은 편이다.","")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        radioGroup = findViewById(R.id.options)
        mProgress = findViewById(R.id.main_screen_progress_bar)

        questionTextView = findViewById(R.id.questionTextView)
        questionTextNumber = findViewById(R.id.questions_no)
        questionTextNumberProgress = findViewById(R.id.progress1)

        questions = questions.shuffled()
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


        nextButton.setOnClickListener {
            val isSelected = radioGroup.checkedRadioButtonId
            if (isSelected == -1) {
                Toast.makeText(this@HomeActivity, "답변을 선택해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                radioGroup.clearCheck()
                nextButton.setBackgroundResource(R.drawable.main_screen_button_non)
                goToNextQuestion()
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

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {  //text 표시
        questionTextView.text = questions[currentQuestionIndex].first
        questionTextNumber.text = "${currentQuestionIndex + 1}."
        questionTextNumberProgress.text = (currentQuestionIndex + 1).toString()
    }
    private fun goToNextQuestion() {    //바꾸는 것
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        setQuestion()
        // Show the back button when moving to the next question
        backButton.visibility = View.VISIBLE
    }
    private fun goToBackQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
        if (currentQuestionIndex < 0) {
            currentQuestionIndex += questions.size
        }
        setQuestion()
    }

}