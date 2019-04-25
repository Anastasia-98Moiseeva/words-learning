package com.example.words_learning.fragments.learnSet

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
import com.example.words_learning.adapters.ImageListAdapter


class LearnSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Learn set"

    val arrayOfItems = arrayOf(
        "Cards",
        "Matching",
        "Test",
        "Writing"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_learn_set, container, false)

        layout = createButtons(layout)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout

    }*/


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

        recycler.adapter = ImageListAdapter(createButtons(), ::onButtonClick)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    /*private fun createClickableList(recycler : RecyclerView) {
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

        recycler.adapter = ClickableAdapter(arrayOfItems)
    }*/

    private fun createButtons(): Array<String>{
        return arrayOfItems
    }

    private fun onButtonClick(position: Int) {
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

}

