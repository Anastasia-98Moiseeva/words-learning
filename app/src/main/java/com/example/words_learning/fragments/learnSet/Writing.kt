package com.example.words_learning.fragments.learnSet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import kotlinx.android.synthetic.main.writing.view.*


class Writing : Fragment() {

    private lateinit var router: Router
    val name = "Learn set"

    private var word_number : Int = 0
    private var savedState : Bundle? = null
    private var createdStateInDestroyView: Boolean = false
    private var saved : String = "saved_bundle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            savedState = savedInstanceState.getBundle(saved)
        }

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.writing, container,
            false)

        if (savedState != null) {
            word_number = savedState!!.getInt(saved)
        }

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        val translation = layout.textView1
        val editText = layout.editText2
        val check = layout.button
        val answer = layout.textView5
        val next = layout.imageButton3
        val back = layout.imageButton4

        printAnswerAndSwitch(translation, editText, check, answer, next, back)

        return layout
    }

    private fun printAnswerAndSwitch(translation: TextView, editText: EditText,
                                    check: Button, answer : TextView, next : ImageButton, back : ImageButton) {
        if (word_number < arrWords.size - 1 && word_number > -1) {
            translation.text = arrOfTranslations[word_number]

            val word: String = editText.text.toString()

            editText.run {
                setText("")
            }

            answer.text = ""

            check.setOnClickListener {
                answer.text = arrWords[word_number]
            }
            next.setOnClickListener {
                word_number += 1
                printAnswerAndSwitch(translation, editText, check, answer, next, back)
            }
            back.setOnClickListener {
                word_number -= 1
                printAnswerAndSwitch(translation, editText, check, answer, next, back)
            }

        } else {
            router.navigateTo(true, ::LearnSetFragment)
        }
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savedState = saveState()
        createdStateInDestroyView = true
        word_number = 0
    }

    private fun saveState(): Bundle {
        val state = Bundle()
        state.putInt(saved, word_number)
        return state
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (word_number == 0) {
            outState.putBundle(saved, savedState)
        } else {
            outState.putBundle(saved, if (createdStateInDestroyView) savedState else saveState())
        }
        createdStateInDestroyView = false
        super.onSaveInstanceState(outState)
    }















    val arrWords : Array<String> = arrayOf("cat", "dog", "rat", "giraffe", "elephant", "cow")
    val arrOfTranslations : Array<String> = arrayOf( "кошка", "собака", "крыса", "жираф", "слон", "корова")


}