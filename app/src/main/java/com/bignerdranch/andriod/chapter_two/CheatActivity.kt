package com.bignerdranch.andriod.chapter_two

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.andriod.chapter_two.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.andriod.chapter_two.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.andriod.chapter_two.answer_shown"
private const val KEY_ANSWER_SHOWN = "answer_shown"

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    private var answerShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerShown = savedInstanceState?.getBoolean(KEY_ANSWER_SHOWN, false) ?: false

        if (answerShown) {
            showAnswer()
            setAnswerShownResult(true)
        }

        binding.showAnswerButton.setOnClickListener {
            showAnswer()
            setAnswerShownResult(true)
        }
    }

    private fun showAnswer() {
        val answerText = if (answerIsTrue) "True" else "False"
        binding.answerTextView.text = answerText
        answerShown = true
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_ANSWER_SHOWN, answerShown)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}