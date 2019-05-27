package com.example.words_learning.fragments.learnSet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router

class Cards : Fragment() {

    val arrWords : Array<String> = arrayOf("cat", "dog", "rat", "giraffe", "elephant", "cow")
    val arrOfTranslations : Array<String> = arrayOf( "кошка", "собака", "крыса", "жираф", "слон", "корова")

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
        var layout = inflater.inflate(R.layout.cards, container, false)

        if (savedState != null) {
            word_number = savedState!!.getInt(saved)
        }

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

        val back = activity!!.findViewById<ImageButton>(R.id.imageButton4)

        switch(next, back)

    }

    fun setState(i : Int) {
        val frontText = activity!!.findViewById<TextView>(R.id.front)
        frontText.setText(arrOfTranslations[i])
        val backText = activity!!.findViewById<TextView>(R.id.back)
        backText.setText(arrWords[i])
    }

    fun switch(next : ImageButton, back : ImageButton){

        if (word_number < arrWords.size - 1 && word_number > -1) {
            setState(word_number)
            next.setOnClickListener {
                word_number += 1
                switch(next, back)
            }
            back.setOnClickListener {
                word_number -= 1
                switch(next, back)
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
}