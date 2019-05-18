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
import com.example.words_learning.adapters.ChooseSetAdapter
//import com.example.words_learning.adapters.AllSets
import com.example.words_learning.database.set.Sets
import java.lang.IllegalStateException

class ChooseSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Choose set"
    private lateinit var set: Sets


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
        set = Sets(activity!!)

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container,
            false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            RecyclerView.VERTICAL,
            false
        )


        val setsNames = set.getSetsNames()

        if (setsNames != null) {
            recycler.adapter = ChooseSetAdapter(set, setsNames, ::onButtonClick)
        }

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name

        return layout
    }

    private fun onButtonClick(position: Int)  = when (position) {
        0 -> router.navigateTo(true, ::LearnSetFragment)
        1 -> router.navigateTo(true, ::LearnSetFragment)
        2 -> router.navigateTo(true, ::LearnSetFragment)
        3 -> router.navigateTo(true, ::LearnSetFragment)
        4 -> router.navigateTo(true, ::LearnSetFragment)
        5 -> router.navigateTo(true, ::LearnSetFragment)
        6 -> router.navigateTo(true, ::LearnSetFragment)
        else -> throw IllegalStateException()

    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }


 /*   companion object {
        private val arrayOfItems: ArrayList<AllSets>
            get() =
                arrayListOf(
                    AllSets("Set1"),
                    AllSets("Set2"),
                    AllSets("Set3"),
                    AllSets("Set4"),
                    AllSets("Set5"),
                    AllSets("Set6")
                )
    }
    */
}

