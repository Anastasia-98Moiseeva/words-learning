package com.example.words_learning.fragments.learnSet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.list.LayoutFragment
import kotlinx.android.synthetic.main.fragment_main.view.*


class EditSetFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_learn_set, container, false)

        layout = createButtons(layout)
        layout.textView2.setText("Learn set")


        return layout

    }


    private fun createButtons(layout: View) : View{

        return layout
    }


}

