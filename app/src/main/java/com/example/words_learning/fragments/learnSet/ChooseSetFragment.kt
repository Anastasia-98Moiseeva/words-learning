package com.example.words_learning.fragments.learnSet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import ru.mail.technotrack.recyclerview.adapters.ClickableButtonAdapter


class ChooseSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Choose set"

    val arrayOfItems : Array<String> = arrayOf(
        "Set1",
        "Set2",
        "Set3",
        "Set4",
        "Set5",
        "Set6",
        "Set7",
        "Set8",
        "Set9",
        "Set10",
        "Set11",
        "Set12",
        "Set13",
        "Set14",
        "Set15",
        "Set16"
    )

    val arrayOfImages : Array<Int> = arrayOf(
        R.drawable.ic_furniture1,
        R.drawable.ic_profession1,
        R.drawable.ic_stationery1,
        R.drawable.ic_body1
    )

    val textSize : Float = 20F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_list, container,
            false)

        val recycler: RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(
            inflater.context,
            RecyclerView.VERTICAL,
            false
        )
        recycler.adapter = ClickableButtonAdapter(createButtons(),
            R.layout.button_layout, textSize,
            arrayOfImages, false, ::onButtonClick)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun onButtonClick(position: Int) {
    }

    private fun createButtons(): Array<String> {
        return arrayOfItems
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }
}

