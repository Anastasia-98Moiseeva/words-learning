package com.example.words_learning.fragments.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.list.LayoutFragment
import kotlinx.android.synthetic.main.fragment_learn_set.view.*

class SearchWord : Fragment() {

    private lateinit var router : Router
    val name = "Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_search_word, container, false)

        layout = createButtons(layout)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
        val searchView = layout.findViewById<SearchView>(R.id.editText)
        searchView.set
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

