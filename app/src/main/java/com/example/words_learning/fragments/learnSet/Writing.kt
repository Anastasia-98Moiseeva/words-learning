package com.example.words_learning.fragments.learnSet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import kotlinx.android.synthetic.main.fragment_choose_set.view.*
import kotlinx.android.synthetic.main.writing.*
import kotlinx.android.synthetic.main.writing.view.*
import java.util.zip.Inflater

class Writing : Fragment() {

    private lateinit var router: Router
    val name = "Learn set"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.writing, container,
            false)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        val word_number = 0

        /*val translation = layout.textView1
        translation.setText(arrOfTranslations[word_number])*/
        val translation = layout.textView1

        val editText = layout.editText2

        val check = layout.button

        val answer = layout.textView5

        val next = layout.imageButton3

        printAnswerAndSwich(word_number, translation, editText, check, answer, next)

        return layout
    }

    fun printAnswerAndSwich(word_number : Int, translation: TextView, editText: EditText,
                            check: Button, answer : TextView, next : ImageButton){
        translation.text = arrOfTranslations[word_number]

        val word: String = editText.text.toString()

        editText.run {

            setText("")
        }
        answer.text = ""

        check.setOnClickListener {
            answer.text = arrWords[word_number]
        }

        if (word_number < arrWords.size - 1) {
            next.setOnClickListener {
                printAnswerAndSwich(word_number + 1, translation, editText, check, answer, next)
            }
        } else {
            router.navigateTo(true, ::LearnSetFragment)
        }

    }













    val arrWords : Array<String> = arrayOf("cat", "dog", "rat", "giraffe", "elephant", "cow")
    val arrOfTranslations : Array<String> = arrayOf( "кошка", "собака", "крыса", "жираф", "слон", "корова")


}