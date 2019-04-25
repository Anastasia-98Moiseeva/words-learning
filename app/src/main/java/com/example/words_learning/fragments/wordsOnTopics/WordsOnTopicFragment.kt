package com.example.words_learning.fragments.wordsOnTopics


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
import com.example.words_learning.adapters.ClickableAdapter

class WordsOnTopicFragment : Fragment() {

    private lateinit var router : Router
    val name = "Words On Topic"

    val arrayOfItems = arrayOf(
        "Vegetables",
        "Dishes",
        "Fruits",
        "Family",
        "Flowers",
        "Footwear",
        "Animals",
        "Clothes",
        "Face",
        "House",
        "Berries",
        "Furniture",
        "Professions",
        "Writing materials",
        "Body parts"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        router = Router(requireActivity(), R.id.fragment_container)

    }

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_words_on_topic, container, false)

        layout = createButtons(layout)
        //layout.textView2.setText("Words on topic")
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout

    }*/


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        createClickableList(recycler)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun createClickableList(recycler : RecyclerView) {
        val layoutManager = GridLayoutManager(
            requireContext(),
            3,
            RecyclerView.VERTICAL,
            false
        )

        recycler.layoutManager = layoutManager
        recycler.adapter = ClickableAdapter(arrayOfItems)
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

