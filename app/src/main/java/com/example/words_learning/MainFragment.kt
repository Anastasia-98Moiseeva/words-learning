package com.example.words_learning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.words_learning.fragments.LayoutFragment
import com.example.words_learning.list.ButtonsListAdapter
import java.lang.IllegalStateException

class MainFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        val buttons : RecyclerView = layout.findViewById(R.id.buttons)

        buttons.layoutManager = LinearLayoutManager(
            inflater.context,
            RecyclerView.VERTICAL,
            false
        )
        buttons.adapter = ButtonsListAdapter(createButtons(), ::onButtonClick)

        return layout
    }

    private fun createButtons() : Array<String> {
        return arrayOf(
            "Home"
        )
    }

    private fun onButtonClick(position : Int) = when(position) {
        0 -> router.navigateTo { R.layout.main_application.getLayoutFragment() }
        else -> throw IllegalStateException()
    }



    private fun Int.getLayoutFragment(): Fragment {
        val fragment = LayoutFragment()
        val args = Bundle()
        args.putInt(LayoutFragment.LAYOUT_KEY, this)
        fragment.arguments = args
        return fragment
    }
}

