package com.example.words_learning.fragments.wordsOnTopics


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.list.LayoutFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.main_application.view.*

class WordsOnTopicFragment : Fragment() {

    private lateinit var router : Router
    val name = "Words On Topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_words_on_topic, container, false)

        layout = createButtons(layout)
        //layout.textView2.setText("Words on topic")
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout

    }


    private fun createButtons(layout: View) : View{
        return layout
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

}

