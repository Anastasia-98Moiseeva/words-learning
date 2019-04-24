package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.fragments.dictionary.adapters.ClickableAdapter
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
        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        createClickableList(recycler)

        //layout = createButtons(layout)


        return layout

    }

    private fun createClickableList(recycler : RecyclerView) {
        val layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 0) 2 else 1
            }
        }
        recycler.layoutManager = layoutManager
        recycler.adapter = ClickableAdapter()
    }

/*
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
    */
}

