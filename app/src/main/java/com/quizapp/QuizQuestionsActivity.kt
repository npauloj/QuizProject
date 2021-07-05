package com.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.quizapp.repository.Repository
import com.quizapp.utils.Constants
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.activity_quiz_questions.view.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private var mUserName: String? = null
    private var countdownInterval = 30
    private var countdownTick = 1000L

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestionsReserva()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_option_five.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private var mCountDownTimer: CountDownTimer = object : CountDownTimer(countdownInterval*countdownTick,  countdownTick){
        override fun onTick(timeRemaining: Long) {
            countdown_progressbar.progress = (countdownInterval-(timeRemaining/1000)).toInt()

            //Log.d("xanfrels",countdown_progressbar.progress.toString())
            Log.d("xanfrels",(countdownInterval-(timeRemaining/1000)).toString())


            tv_countdown.text = (timeRemaining/1000).toString()
        }
        override fun onFinish() {
            changeEnableOptions(false)
        }
    }




    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv_option_one -> {

                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(tv_option_four, 4)
            }

            R.id.tv_option_five -> {

                selectedOptionView(tv_option_five, 5)
            }

            R.id.btn_submit -> {
                mCountDownTimer.cancel()

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            val intent = Intent(this@QuizQuestionsActivity, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {

                    Log.d("xanfrels", "clicou no submit2")
                    changeEnableOptions(false)



                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // Check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) btn_submit.text = getString(R.string.end)
                    else btn_submit.text = getString(R.string.next_question)

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    // Function for setting the question to UI components.

    private fun setQuestion() {


        val repository= Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getQuestionsApi()
        viewModel.myResponse.observe(this, Observer {response ->
           val id = response.id
            tv_question.text = response.statement
            tv_option_one.text = response.options[0]
            tv_option_two.text = response.options[1]
            tv_option_three.text = response.options[2]
            tv_option_four.text = response.options[3]
            tv_option_five.text = response.options[4]
        })

        // Getting the question from the list with the help of current position.
        // val question = mQuestionsList!!.get(mCurrentPosition - 1)

        mCountDownTimer.start()
        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = getString(R.string.finish)
        } else btn_submit.text = getString(R.string.submit)

        progressBar.progress = mCurrentPosition
        tv_progress.text = mCurrentPosition.toString() + "/" + progressBar.max

        /*

        tv_question.text = question.question
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        tv_option_five.text = question.optionFive
*/

        changeEnableOptions(true)
    }

    private fun changeEnableOptions(enable:Boolean) {
        tv_option_one.isEnabled = enable
        tv_option_two.isEnabled = enable
        tv_option_three.isEnabled = enable
        tv_option_four.isEnabled = enable
        tv_option_five.isEnabled = enable
    }

    // Function to set the view of selected option view.
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.selected_option_border_bg
        )
    }

    // Function to set default options view when the new question is loaded

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)
        options.add(4, tv_option_five)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    R.drawable.default_option_border_bg
            )
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        Log.d("xanfrels", "clicou no submit3 ")
        when (answer) {

            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                        this@QuizQuestionsActivity,
                        drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                        this@QuizQuestionsActivity,
                        drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                        this@QuizQuestionsActivity,
                        drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                        this@QuizQuestionsActivity,
                        drawableView
                )
            }
            5 -> {
                tv_option_five.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
        }
    }
}