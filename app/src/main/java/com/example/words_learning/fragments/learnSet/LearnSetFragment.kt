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

import ru.mail.technotrack.recyclerview.adapters.ClickableButtonAdapter


class LearnSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Learn set"

    val arrayOfItems : Array<String> = arrayOf(
        "Cards",
        "Matching",
        "Test",
        "Writing"
    )

    val arrayOfImages : Array<Int> = arrayOf(
        R.drawable.ic_test1,
        R.drawable.ic_test2,
        R.drawable.ic_test3,
        R.drawable.ic_test4

    )

    val textSize : Float = 15F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }
//paging library overview for ip
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list,
            container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

        recycler.adapter = ClickableButtonAdapter(createButtons(),
            R.layout.test_image_button_layout, textSize,
            arrayOfImages, true, ::onButtonClick)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

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

