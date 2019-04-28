package com.example.words_learning.fragments.dictionary

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
import com.example.words_learning.adapters.LearnSetAdapter
import com.example.words_learning.adapters.SpiritTeacher

class ChooseSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Choose set"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

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
        recycler.adapter = LearnSetAdapter(arrayOfItems)


        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun onButtonClick(position: Int) {
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }


    companion object {
        private val arrayOfItems: ArrayList<SpiritTeacher>
            get() =
                arrayListOf(
                    SpiritTeacher("Set1"),
                    SpiritTeacher("Set2"),
                    SpiritTeacher("Set3"),
                    SpiritTeacher("Set4"),
                    SpiritTeacher("Set5"),
                    SpiritTeacher("Set6")
                )
    }
}

