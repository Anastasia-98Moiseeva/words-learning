package com.example.words_learning.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.IllegalStateException
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.list.ButtonsListAdapter

class MainFragment : Fragment() {

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        //val buttons : RecyclerView = layout.findViewById(R.id.buttons)

      //  buttons.layoutManager = LinearLayoutManager(
      //      inflater.context,
      //      RecyclerView.VERTICAL,
      //      false
      //  )
      //  buttons.adapter = ButtonsListAdapter(createButtons(), ::onButtonClick)

        return layout
    }

    private fun createButtons() : Array<String> {
        return arrayOf(
            "FrameLayout",
            "LinearLayout",
            "RelativeLayout",
            "ConstraintLayout",
            "Widgets",
            "Start Activity for result",
            "Open Mail.ru",
            "Open Technotrack"
        )
    }

    private fun onButtonClick(position : Int) = when(position) {
        0 -> router.navigateTo { getLayoutFragment(R.layout.frame_layout) }
        1 -> router.navigateTo { getLayoutFragment(R.layout.linear_layout) }
        2 -> router.navigateTo { getLayoutFragment(R.layout.relative_layout) }
        3 -> router.navigateTo { getLayoutFragment(R.layout.constraint_layout) }
        4 -> router.navigateTo (fragmentFactory = ::WidgetsFragment)
        5 -> router.navigateTo (fragmentFactory = ::ActivityResultFragment)
        6 -> launchBrowser()
        7 -> openTechnoTrack()
        else -> throw IllegalStateException()
    }

    private fun openTechnoTrack() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https://track.mail.ru/android")
        startActivity(intent)
    }

    private fun launchBrowser() {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("http://mail.ru")
        startActivity(intent)
    }

    private fun getLayoutFragment(layout : Int) : Fragment {
        val fragment = LayoutFragment()
        val args = Bundle()
        args.putInt(LayoutFragment.LAYOUT_KEY, layout)
        fragment.arguments = args
        return fragment
    }
}

