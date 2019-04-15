package com.example.words_learning.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.list.LayoutFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
class WordsOnTopicFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_words_on_topic, container, false)

        layout = createButtons(layout)


        return layout

    }


    private fun createButtons(layout: View) : View{
        return layout
    }



    private fun clickEvent(layout: View) {
        //router.navigateTo(false, ::)
        //router.navigateTo { getLayoutFragment(R.layout.fragment_words) }
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

