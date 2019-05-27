package com.example.words_learning.fragments.dictionary

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.adapters.DeletedListAdapter
import com.example.words_learning.database.dictionary.Dictionary
import com.example.words_learning.database.dictionary.Words
import com.example.words_learning.database.statistic.Statistic

class DictionaryFragment : Fragment() {

    private lateinit var dictionary: Dictionary
    private lateinit var statistic: Statistic
    private lateinit var router : Router
    val name = "Dictionary"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
        dictionary = Dictionary(activity!!)
        statistic = Statistic(activity!!)

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            RecyclerView.VERTICAL,
            false
        )


        dictionary.fillWordsTranslation()
        recycler.adapter = DeletedListAdapter(layout, dictionary, statistic)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout
    }


    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name
    }

    private fun getWords() {
        var db: SQLiteDatabase


    }

}

