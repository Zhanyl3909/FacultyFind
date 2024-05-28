package com.example.hackathon

import android.annotation.SuppressLint
import android.content.Context
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

    private val europe = Score()
    private val asia = Score()
    private val university = Score()
    private val it = Score()
    private val digital = Score()
    private val social = Score()
    

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
        Pair("다양한 예술 형식에 관심이 있어서 미술전이나 영화제를 자주 찾아다닌다.","digital"),

        Pair("사회적 변화와 인간 관계에 대한 이해를 통해 세계를 더 나은 곳으로 만들고자 한다.","social"),
        Pair("데이터 분석과 통계를 활용하여 사회현상을 탐구하고 해석하는 것에 흥미를 느낀다.","social"),
        Pair("다양한 문화와 역사에 대해 배우고 이해하는 것을 즐기며, 사회 다양성에 관심이 많다.","social"),
        Pair("새로운 아이디어나 사회 문제에 대해 논의하고 탐구하는 것을 즐긴다.","social")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        resetScores(this)

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
                goAddToScore(questions[currentQuestionIndex].second, selectedOptionId)

                if (loadScore(this, currentQuestionIndex+1) == 0) {
                    radioGroup.clearCheck()
                    nextButton.setBackgroundResource(R.drawable.main_screen_button_non)
                } else {
                    radioGroup.check(loadScore(this, currentQuestionIndex))
                }

                saveScore(this, currentQuestionIndex, selectedOptionId)

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
        resetScores(this)
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

    private fun goToBackQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1) % questions.size
        if (currentQuestionIndex < 0) {
            currentQuestionIndex += questions.size
        }
        setQuestion()
        goMinusToScore(questions[currentQuestionIndex].second)
        radioGroup.clearCheck()
        radioGroup.check(loadScore(this, currentQuestionIndex))
    }

    private fun goAddToScore (major: String, radiobuttonID: Int) {
        when (major) {
            "europe" -> europe.addTheScore(radiobuttonID)
            "asia" -> asia.addTheScore(radiobuttonID)
            "university" -> university.addTheScore(radiobuttonID)
            "it" -> it.addTheScore(radiobuttonID)
            "digital" -> digital.addTheScore(radiobuttonID)
            "social" -> social.addTheScore(radiobuttonID)
        }
    }
    private fun goMinusToScore (major: String) {
        when (major) {
            "europe" -> europe.minusTheScore()
            "asia" -> asia.minusTheScore()
            "university" -> university.minusTheScore()
            "it" -> it.minusTheScore()
            "digital" -> digital.minusTheScore()
            "social" -> social.minusTheScore()
        }
    }
    private fun showResultScreen() {
        val intent = Intent(this@HomeActivity, ResultActivity::class.java)
        intent.putExtra("Europe_KEY", europe.sumScore())
        intent.putExtra("Asia_KEY", asia.sumScore())
        intent.putExtra("University_KEY", university.sumScore())
        intent.putExtra("IT_KEY", it.sumScore())
        intent.putExtra("Digital_KEY", digital.sumScore())
        intent.putExtra("Social_KEY", social.sumScore())
        startActivity(intent)
        finish()
    }

    private fun saveScore(context: Context, sequence: Int, selectKey: Int) {
        val sharedPreferences = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(sequence.toString(), selectKey)
        editor.apply()
    }

    private fun loadScore(context: Context, sequence: Int): Int {
        val sharedPreferences = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(sequence.toString(), 0) // 기본값으로 0을 반환
    }

    private fun resetScores(context: Context) {
        val sharedPreferences = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // 모든 데이터 삭제
        editor.apply() // 변경사항 적용
    }


}

