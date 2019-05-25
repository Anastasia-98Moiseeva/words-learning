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
import eu.davidea.flipview.FlipView
import kotlinx.android.synthetic.main.cards.*
import kotlinx.android.synthetic.main.cards.view.*
import kotlinx.android.synthetic.main.fragment_search_word.*
import kotlinx.android.synthetic.main.writing.view.*

class Cards : Fragment() {

    val arrWords : Array<String> = arrayOf("cat", "dog", "rat", "giraffe", "elephant", "cow")
    val arrOfTranslations : Array<String> = arrayOf( "кошка", "собака", "крыса", "жираф", "слон", "корова")

    private lateinit var router: Router
    val name = "Learn set"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var layout = inflater.inflate(R.layout.cards, container, false)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout

    }

    override fun onStart() {
        super.onStart()

        val frontText = activity!!.findViewById<TextView>(R.id.front)
        frontText.setText(arrOfTranslations[0])
        val backText = activity!!.findViewById<TextView>(R.id.back)
        backText.setText(arrWords[0])

        val next = activity!!.findViewById<ImageButton>(R.id.imageButton3)

        switch(1, next)

    }

    fun setState(i : Int) {
        val frontText = activity!!.findViewById<TextView>(R.id.front)
        frontText.setText(arrOfTranslations[i])
        val backText = activity!!.findViewById<TextView>(R.id.back)
        backText.setText(arrWords[i])
    }

    fun switch(word_number : Int, next : ImageButton){

        setState(word_number)

        if (word_number < arrWords.size - 1) {
            next.setOnClickListener {
                switch(word_number + 1, next)
            }
        } else {
            router.navigateTo(true, ::LearnSetFragment)
        }
    }
}