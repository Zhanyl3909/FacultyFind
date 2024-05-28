package com.example.hackathon

import DetailsActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {


    val europe = Score()
    val asia = Score()
    val university = Score()
    val it = Score()
    val digital = Score()
    

    private lateinit var mProgress: ProgressBar


    private lateinit var backButton: RelativeLayout
    private lateinit var nextButton: RelativeLayout
    private lateinit var radioGroup: RadioGroup

    private lateinit var questionTextView: TextView
    private lateinit var questionTextNumber: TextView
    private lateinit var questionTextNumberProgress: TextView


    private var currentQuestionIndex = 0
    private var questions = listOf (
        Pair("다양한 문화와 언어에 관심이 많아서 해외 여행이나 교환학생 프로그램에 참여하고 싶은 편이다.","europe"),
        Pair("유럽이나 미주의 역사나 문화에 대해 탐구하고 공부하는 것을 좋아한다.","europe"),
        Pair("외국어를 배우는 것에 열정이 있어서 언어 학습 앱이나 온라인 강의를 이용하여 새로운 언어를 익히는 것을 좋아한다.","europe"),
        Pair("국제 관계나 다문화 사회에 대해 관심을 가지고, 세계적인 이슈에 대해 논의하는 것을 좋아한다.","europe"),

        Pair("아시아 문화나 역사에 대한 이해와 관심이 많아서 아시아 국가들을 여행하고 싶은 편이다.","asia"),
        Pair("아시아 지역의 경제 발전과 기술 혁신에 관심을 가지며, 그에 대한 연구나 분석을 하고 싶은 마음이 있다.","asia"),
        Pair("다양한 아시아 문화 예술에 대해 관심이 있어서 전통 음악이나 미술 전시회를 자주 찾아다닌다.","asia"),
        Pair("아시아 지역의 정치, 사회, 경제 문제에 대해 관심을 가지고 다양한 관점에서 이해하려고 노력하는 편이다.","asia"),

        Pair("경제나 비즈니스에 관심이 많아서 주식 시장이나 금융 상품에 대한 정보를 찾아보는 편이다.","university"),
        Pair("계획을 세우고 그에 따라 행동하는 것을 선호하는 편이다.","university"),
        Pair("경제 문제에 대해 다양한 관점에서 바라보고 그에 대한 해결책을 고민하는 것을 즐긴다.","university"),
        Pair("현재의 경제 상황에 대해 이해하고 미래의 경제 동향을 예측하는 것에 흥미를 느낀다.","university"),

        Pair("컴퓨터 프로그래밍이나 코딩을 배우고 응용하는 것에 흥미를 느낀다.","it"),
        Pair("새로운 기술이나 디지털 도구를 활용하여 문제를 해결하는 것을 즐기는 편이다.","it"),
        Pair("소프트웨어 개발이나 웹 디자인 등에 관심이 있어서 관련된 지식을 습득하는 것을 좋아한다.","it"),
        Pair("IT 산업의 트렌드나 기술 발전에 대해 항상 관심을 가지고 있으며, 그에 대한 정보를 주로 탐색한다.","it"),

        Pair("스토리텔링이나 시나리오 작성에 흥미를 가지며, 자신의 이야기를 창작하는 것을 좋아한다.","digital"),
        Pair("영상이나 사진을 통해 메시지를 전달하거나 표현하는 것에 관심이 있다. ","digital"),
        Pair("영화나 드라마를 감상하고 그 속에서 감정을 공감하며 즐긴다.","digital"),
        Pair("영화나 드라마를 감상하고 그 속에서 감정을 공감하며 즐긴다.","digital")
    )



    @SuppressLint("MissingInflatedId")
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

        // Random
        // questions = questions.shuffled()
        setQuestion()


        //for view detail

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Enable the next button if a radio button is selected
            nextButton.isEnabled = checkedId != -1
            nextButton.setBackgroundResource(R.drawable.main_screen_button)
        }


        //this makes change text on the last question to see the result
        nextButton.setOnClickListener {
            val isSelected = radioGroup.checkedRadioButtonId
            if (isSelected == -1) {
                Toast.makeText(this@HomeActivity, "답변을 선택해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                if (currentQuestionIndex == 18) {
                    nextButton.findViewById<TextView>(R.id.text_for_nextButton).text = "결과 보기"
                }
                val selectedOptionId = radioGroup.checkedRadioButtonId
                goToScore(questions[currentQuestionIndex].second, selectedOptionId)
                radioGroup.clearCheck()
                nextButton.setBackgroundResource(R.drawable.main_screen_button_non)

                if (currentQuestionIndex == 19) {
                    showResultScreen()
                } else {
                    goToNextQuestion()
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
    fun onClickTryAgain(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }



    @SuppressLint("SetTextI18n")
    private fun setQuestion() {  //text 표시
        questionTextView.text = questions[currentQuestionIndex].first
        questionTextNumber.text = "${currentQuestionIndex + 1}."
        questionTextNumberProgress.text = (currentQuestionIndex + 1).toString()
        mProgress.progress = currentQuestionIndex + 1
    }
    private fun goToNextQuestion() {    //바꾸는 것
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        setQuestion()
        // Show the back button when moving to the next question
        backButton.visibility = View.VISIBLE
    }

    private fun goToScore (major: String, radiobuttonID: Int) {
        when (major) {
            "europe" -> europe.addTheScore(radiobuttonID)
            "asia" -> asia.addTheScore(radiobuttonID)
            "university" -> university.addTheScore(radiobuttonID)
            "it" -> it.addTheScore(radiobuttonID)
            "digital" -> digital.addTheScore(radiobuttonID)
        }
    }



    private fun goToBackQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
        if (currentQuestionIndex < 0) {
            currentQuestionIndex += questions.size
        }
        setQuestion()
    }
    private fun showResultScreen() {
        val intent = Intent(this@HomeActivity, ResultActivity::class.java)
        intent.putExtra("Europe_KEY", europe.majorScore)
        intent.putExtra("Asia_KEY", asia.majorScore)
        intent.putExtra("University_KEY", university.majorScore)
        intent.putExtra("IT_KEY", it.majorScore)
        intent.putExtra("Digital_KEY", digital.majorScore)

        //for the details
        startActivity(intent)
    }




}

