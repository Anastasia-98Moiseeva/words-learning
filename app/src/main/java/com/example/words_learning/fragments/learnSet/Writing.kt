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
import com.example.words_learning.database.set.Sets
import com.example.words_learning.message
import kotlinx.android.synthetic.main.writing.view.*


class Writing : Fragment() {

    private lateinit var router: Router
    val name = "Learn set"

    private var wordNumber : Int = 0
    private var savedState : Bundle? = null
    private var createdStateInDestroyView: Boolean = false
    private var saved : String = "saved_bundle"
    private var msg : String? = null
    private lateinit var sets : Sets

    private val arrayWordsTranslations = ArrayList<Pair<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sets = Sets(activity!!)

        if (savedInstanceState != null) {
            savedState = savedInstanceState.getBundle(saved)
        }


        if (this.arguments!= null) {
            msg = this.arguments!!.getString(message)
            if (msg != null) {
                val getSet = sets.getSet(msg.toString())
                if (getSet != null) {
                    for (set in getSet) {
                        arrayWordsTranslations.add(Pair(set.word, set.traslation))
                    }
                }
            }
        }

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.writing, container,
            false)

        if (savedState != null) {
            wordNumber = savedState!!.getInt(saved)
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


        if (wordNumber == 0) {
            back.visibility = View.INVISIBLE
        }

        if (wordNumber == arrayWordsTranslations.size - 1) {
            next.visibility = View.INVISIBLE
        }

        if (wordNumber > 0 && (wordNumber < arrayWordsTranslations.size - 1)) {
            next.visibility = View.VISIBLE
            back.visibility = View.VISIBLE
        }

        translation.text = arrayWordsTranslations[wordNumber].second

        val word: String = editText.text.toString()

        editText.run {
            setText("")
        }

        answer.text = ""

        check.setOnClickListener {
            answer.text = arrayWordsTranslations[wordNumber].first
        }

        next.setOnClickListener {
             wordNumber ++
             printAnswerAndSwitch(translation, editText, check, answer, next, back)
        }

        back.setOnClickListener {
            wordNumber --
            printAnswerAndSwitch(translation, editText, check, answer, next, back)
        }

    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savedState = saveState()
        createdStateInDestroyView = true
        wordNumber = 0
    }

    private fun saveState(): Bundle {
        val state = Bundle()
        state.putInt(saved, wordNumber)
        return state
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (wordNumber == 0) {
            outState.putBundle(saved, savedState)
        } else {
            outState.putBundle(saved, if (createdStateInDestroyView) savedState else saveState())
        }
        createdStateInDestroyView = false
        super.onSaveInstanceState(outState)
    }

}