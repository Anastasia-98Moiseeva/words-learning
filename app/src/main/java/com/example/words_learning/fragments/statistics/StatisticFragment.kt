package com.example.words_learning.fragments.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.database.statistic.Statistic
import kotlinx.android.synthetic.main.fragment_statistic.view.*
import kotlinx.android.synthetic.main.main_application.view.*


class StatisticFragment : Fragment() {

    private lateinit var router : Router
    private lateinit var statistic : Statistic

    val name = "Statistics"
    private val numWordsLearned = "Столько слов вы выучили за "
    private val day = "день: "
    private val month = "месяц: "

    val data = Array<Pair<Int, String>>(3) {
        Pair(1, "Месяц")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statistic = Statistic(activity!!)
        router = Router(requireActivity(), R.id.fragment_container)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_statistic, container, false)



        val num = statistic.getNumWordsDate(0)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout

    }

    @SuppressLint("SetTextI18n")
    fun viewStatistic(textView : TextView, numDays : Int) {
        val num = statistic.getNumWordsDate(numDays)
        textView.text =  numWordsLearned + month + num.toString()
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name
    }

}

