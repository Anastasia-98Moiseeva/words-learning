package com.example.words_learning.fragments.dictionary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.adapters.ClickableAdapter
import com.example.words_learning.list.LayoutFragment


class DictionaryFragment : Fragment() {

    private lateinit var router : Router
    val name = "Dictionary"

    val arrayOfItems = arrayOf(
        "lorem",
        "ipsum",
        "dolor",
        "sit",
        "amet",
        "consectetuer",
        "adipiscing",
        "elit",
        "morbi",
        "vel",
        "ligula",
        "vitae",
        "arcu",
        "aliquet",
        "mollis",
        "etiam",
        "vel",
        "erat",
        "placerat",
        "ante",
        "porttitor",
        "sodales",
        "pellentesque",
        "augue",
        "purus"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

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
        recycler.adapter = ClickableAdapter(arrayOfItems)
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

}

