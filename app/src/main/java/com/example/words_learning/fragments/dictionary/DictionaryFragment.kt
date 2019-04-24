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
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.fragments.dictionary.adapters.ClickableAdapter
import com.example.words_learning.list.LayoutFragment


class DictionaryFragment : Fragment() {

    companion object {
        private const val STYLE_KEY = "STYLE_KEY"

        fun createListFragment(style : Int) : Fragment {
            val fragment = DictionaryFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)

        val style = arguments?.getInt(STYLE_KEY) ?: 0

        when(style) {
            0 -> createSimpleList(recycler)
            //CLICKABLE_LIST -> createClickableList(recycler)
            //PAGES -> createPageList(recycler)
        }

        return layout
    }

    private fun createSimpleList(recycler : RecyclerView) {
        recycler.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
            //recycler.adapter = SimpleListAdapter()
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

    private fun createPageList(recycler: RecyclerView) {
        recycler.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )

        PagerSnapHelper().attachToRecyclerView(recycler)
     //   recycler.adapter = PageAdapter()
    }
}

