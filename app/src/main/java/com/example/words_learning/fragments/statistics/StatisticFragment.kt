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
    private val numWordsLearned = "You have learned:"
    private val day = " in a day "
    private val week = " in a week "
    private val twoWeek = " in two weeks "

    /*val data = Array<Pair<Int, String>>(3) {
        Pair(1, "Месяц")

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statistic = Statistic(activity!!)
        router = Router(requireActivity(), R.id.fragment_container)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_statistic, container, false)

        val numInDay = statistic.getNumWordsDate(0) // выводит кол-во изученных слов за ласт дни, в скоб скок дней.
        val numInWeek = statistic.getNumWordsDate(6)
        val numInTwoWeek = statistic.getNumWordsDate(13K)

        val textView = layout.findViewById<TextView>(R.id.textView6)
        textView.text = numWordsLearned

        val textViewDay = layout.findViewById<TextView>(R.id.textView1)
        textViewDay.text = makeStr(day, numInDay)

        val textViewWeek = layout.findViewById<TextView>(R.id.textView3)
        textViewWeek.text = makeStr(week, numInWeek)

        val textViewMonth = layout.findViewById<TextView>(R.id.textView4)
        textViewMonth.text = makeStr(twoWeek, numInTwoWeek)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout
    }

    private fun makeStr (str : String, num : Int) : String {
        return if (num != 1) {
            " $num words$str"
        } else {
            " $num word$str"
        }
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name
    }

}

