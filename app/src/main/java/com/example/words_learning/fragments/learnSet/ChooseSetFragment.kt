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
import com.example.words_learning.adapters.DeletedListAdapter
import com.example.words_learning.adapters.SpiritualTeacher

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
        recycler.adapter = DeletedListAdapter(arrayOfItems, R.layout.fragment_choose_set)


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
        private val arrayOfItems: ArrayList<SpiritualTeacher>
            get() =
                arrayListOf(
                    SpiritualTeacher("Set1", "Set1"),
                    SpiritualTeacher("Set2", "Set1"),
                    SpiritualTeacher("Set3", "Set1"),
                    SpiritualTeacher("Set4", "Set1"),
                    SpiritualTeacher("Set5", "Set1"),
                    SpiritualTeacher("Set6", "Set1")
                )
    }
}

