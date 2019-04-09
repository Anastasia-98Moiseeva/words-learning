package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.words_learning.fragments.LayoutFragment
import kotlinx.android.synthetic.main.button_layout.*
import kotlinx.android.synthetic.main.button_layout.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.lang.IllegalStateException

class MainFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_main, container, false)

        layout = createButtons(layout)


        return layout

    }


    private fun createButtons(layout: View) : View{
        layout.button1.setText("Words")
        layout.button2.setText("Tasks")
        layout.button3.setText("Teacher")
        layout.button4.setText("Statistics")


        layout.button1.setOnClickListener(this::clickEvent)
        return layout
    }



    private fun clickEvent(layout: View) {
        layout.button1.setText("Hello Kitty!")
    }



    //private fun onButtonClick(position : Int) = when(position) {
    //    0 -> router.navigateTo { R.layout.main_application.getLayoutFragment() }
    //    else -> throw IllegalStateException()
    //}



    private fun Int.getLayoutFragment(): Fragment {
        val fragment = LayoutFragment()
        val args = Bundle()
        args.putInt(LayoutFragment.LAYOUT_KEY, this)
        fragment.arguments = args
        return fragment
    }
}

