package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.fragments.StatisticFragment
import com.example.words_learning.fragments.MakeSetFragment
import com.example.words_learning.fragments.LearnSetFragment
import com.example.words_learning.list.LayoutFragment
import com.example.words_learning.fragments.WordsOnTopicFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
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
        layout.button1.setText("Make set")
        layout.button2.setText("Learn set")
        layout.button3.setText("Words on topics")
        layout.button4.setText("Statistics")


        layout.button1.setOnClickListener {
            router.navigateTo(true, ::MakeSetFragment)
        }
        layout.button2.setOnClickListener {
            router.navigateTo(true, ::LearnSetFragment)
        }
        layout.button3.setOnClickListener {
            router.navigateTo(true, ::WordsOnTopicFragment)
        }
        layout.button4.setOnClickListener {
            router.navigateTo(true, ::StatisticFragment)
        }
        return layout
    }



    //private fun onButtonClick(position : Int) = when(position) {
    //    0 -> router.navigateTo { R.layout.main_application.getLayoutFragment() }
    //    else -> throw IllegalStateException()
    //}



    private fun getLayoutFragment(layout : Int) : Fragment {
        val fragment = LayoutFragment()
        val args = Bundle()
        args.putInt(LayoutFragment.LAYOUT_KEY, layout)
        fragment.arguments = args
        return fragment
    }
}

