package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.fragments.statistics.StatisticFragment
import com.example.words_learning.fragments.makeSet.MakeSetFragment
import com.example.words_learning.fragments.learnSet.LearnSetFragment
import com.example.words_learning.fragments.wordsOnTopics.WordsOnTopicFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
class MainFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_main, container, false)

        layout = createButtons(layout)


        return layout

    }


    private fun createButtons(layout: View) : View{
        layout.button1.setText("Make set")
        layout.button2.setText("Learn set")
        layout.button3.setText("Words on topics")
        layout.button4.setText("Statistics")


        layout.button1.setOnClickListener {
            router.navigateTo(true, ::MakeSetFragment)
        }
        layout.button2.setOnClickListener {
            router.navigateTo(true, ::LearnSetFragment)
        }
        layout.button3.setOnClickListener {
            router.navigateTo(true, ::WordsOnTopicFragment)
        }
        layout.button4.setOnClickListener {
            router.navigateTo(true, ::StatisticFragment)
        }
        return layout
    }
}

