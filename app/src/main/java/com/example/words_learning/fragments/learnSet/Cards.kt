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
import com.example.words_learning.database.set.Sets
import com.example.words_learning.message
import kotlinx.android.synthetic.main.fragment_choose_set.*

class Cards : Fragment() {

    //val arrWords : Array<String> = arrayOf("cat", "dog", "rat", "giraffe", "elephant", "cow")
    //val arrOfTranslations : Array<String> = arrayOf( "кошка", "собака", "крыса", "жираф", "слон", "корова")
    /*private val arrayWordsTranslations : Array<Pair<String, String> > = arrayOf(
        Pair("cat", "кошка"),
        Pair("dog", "собака"),
        Pair("rat", "крыса"),
        Pair("giraffe", "жираф"),
        Pair("elephant", "слон"),
        Pair("cow", "корова")
    )*/
    private val arrayWordsTranslations = ArrayList<Pair<String, String>>()

    private lateinit var router: Router
    val name = "Learn set"

    private var word_number : Int = 0
    private var savedState : Bundle? = null
    private var createdStateInDestroyView: Boolean = false
    private var saved : String = "saved_bundle"
    private var msg : String? = null
    private lateinit var sets : Sets
    //private var sets : Se

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.cards, container, false)

        if (savedState != null) {
            word_number = savedState!!.getInt(saved)
        }

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout
    }

    override fun onStart() {
        super.onStart()

        val frontText = activity!!.findViewById<TextView>(R.id.front)
        frontText.text = arrayWordsTranslations[0].first
        val backText = activity!!.findViewById<TextView>(R.id.back)
        backText.text = arrayWordsTranslations[0].second

        val next = activity!!.findViewById<ImageButton>(R.id.imageButton3)

        val back = activity!!.findViewById<ImageButton>(R.id.imageButton4)

        switch(next, back)

    }

    private fun setState(i : Int) {
        val frontText = activity!!.findViewById<TextView>(R.id.front)
        frontText.text = arrayWordsTranslations[i].first
        val backText = activity!!.findViewById<TextView>(R.id.back)
        backText.text = arrayWordsTranslations[i].second
    }

    private fun switch(next : ImageButton, back : ImageButton){

        setState(word_number)

        if (word_number == 0) {
            back.visibility = View.INVISIBLE
        }

        if (word_number == arrayWordsTranslations.size - 1) {
            next.visibility = View.INVISIBLE
        }

        if (word_number > 0 && (word_number < arrayWordsTranslations.size - 1)) {
            next.visibility = View.VISIBLE
            back.visibility = View.VISIBLE
        }

        next.setOnClickListener {
            word_number++
            switch(next, back)
        }

        back.setOnClickListener {
            word_number--
            switch(next, back)
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