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
    private val month = " in a month "

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

        val numInDay = statistic.getNumWordsDate(1) // выводит кол-во изученных слов за ласт дни, в скоб скок дней.
        val numInWeek = statistic.getNumWordsDate(7)
        val numInMonth = statistic.getNumWordsDate(30)

        val textView = layout.findViewById<TextView>(R.id.textView6)
        textView.setText(numWordsLearned)

        val textViewDay = layout.findViewById<TextView>(R.id.textView1)
        textViewDay.setText(makeStr(day, numInDay))

        val textViewWeek = layout.findViewById<TextView>(R.id.textView3)
        textViewWeek.setText(makeStr(week, numInWeek))

        val textViewMonth = layout.findViewById<TextView>(R.id.textView4)
        textViewMonth.setText(makeStr(month, numInMonth))

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout
    }

    fun makeStr (str : String, num : Int) : String {
        if (num != 1) {
            return " " + num.toString() + " words" + str
        } else {
            return " " + num.toString() + " word" + str
        }
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

