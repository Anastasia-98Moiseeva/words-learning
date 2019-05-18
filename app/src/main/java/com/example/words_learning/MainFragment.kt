package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.adapters.ClickableButtonAdapter
import com.example.words_learning.fragments.learnSet.ChooseSetFragment
import com.example.words_learning.fragments.statistics.StatisticFragment
import com.example.words_learning.fragments.makeSet.MakeSetFragment
import com.example.words_learning.fragments.wordsOnTopics.WordsOnTopicFragment
import java.lang.IllegalStateException

class MainFragment : Fragment() {

    private lateinit var router: Router
    val name = "Main menu"

    val arrayOfItems : Array<String> = arrayOf(
        "Make set",
        "Learn set",
        "Words on topics",
        "Statistics"
    )

    val arrayOfImages : Array<Int> = arrayOf(
        R.drawable.ic_furniture1,
        R.drawable.ic_profession1,
        R.drawable.ic_stationery1,
        R.drawable.ic_body1
    )

    val textSize : Float = 18F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_list, container,
            false)
        val recycler: RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(
            inflater.context,
            RecyclerView.VERTICAL,
            false
        )
        recycler.adapter = ClickableButtonAdapter(createButtons(),
            R.layout.button_layout, textSize, arrayOfImages,
            false, ::onButtonClick)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun createButtons(): Array<String> {
        return arrayOfItems
    }

    private fun onButtonClick(position: Int) = when (position) {
        0 -> router.navigateTo(true, ::MakeSetFragment)
        1 -> router.navigateTo(true, ::ChooseSetFragment)
        2 -> router.navigateTo(true, ::WordsOnTopicFragment)
        3 -> router.navigateTo(true, ::StatisticFragment)
        else -> throw IllegalStateException()
    }

    override fun onResume() {
            super.onResume()
            val listView = activity!!.findViewById<TextView>(R.id.textView2)
            listView.setText(name)
    }
}
