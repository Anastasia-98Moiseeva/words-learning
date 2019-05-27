package com.example.words_learning.fragments.wordsOnTopics


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
import com.example.words_learning.adapters.WordsAdapter


class WordsOnTopicFragment : Fragment() {

    private lateinit var router : Router
    val name = "Words On Topic"

    val arrayOfItems : Array<String> = arrayOf(
        "Vegetables",
        "Dishes",
        "Fruits",
        "Family",
        "Flowers",
        "Footwear",
        "Animals",
        "Clothes",
        "Face",
        "House",
        "Berries",
        "Furniture",
        "Professions",
        "Stationery",
        "Body parts"
    )

    private val arrayOfWords : Array<Pair<Int, Int>> = arrayOf(
        Pair(R.drawable.ic_vegetable1, R.array.vegetables),
        Pair(R.drawable.ic_cutlery1, R.array.dishes),
        Pair(R.drawable.ic_fruit1, R.array.fruit),
        Pair(R.drawable.ic_family1, R.array.family),
        Pair(R.drawable.ic_flower1, R.array.flowers),
        Pair(R.drawable.ic_footwear1, R.array.footwear),
        Pair(R.drawable.ic_animal1, R.array.animals),
        Pair(R.drawable.ic_clothes1, R.array.clothes),
        Pair(R.drawable.ic_face1, R.array.face),
        Pair(R.drawable.ic_home1, R.array.house),
        Pair(R.drawable.ic_berry1, R.array.berries),
        Pair(R.drawable.ic_furniture1, R.array.furniture),
        Pair(R.drawable.ic_profession1, R.array.professions),
        Pair(R.drawable.ic_stationery1, R.array.stationary),
        Pair(R.drawable.ic_body1, R.array.body_parts)
    )

    private val textSize : Float = 12F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list,
            container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            3,
            RecyclerView.VERTICAL,
            false
        )
        recycler.adapter = WordsAdapter(createButtons(),
            R.layout.words_image_button_layout, textSize,
            arrayOfWords, true, ::onButtonClick)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name


        return layout
    }


    private fun createButtons(): Array<String>{
        return arrayOfItems
    }

    private fun setFragment(pos : Int, name : Int) {


    }


    private fun onButtonClick(position: Int) {
        val msg = arrayOfWords[position].second
        router.navigateTo(true, ::WordsOnTopicListFragment, transportedMessage = msg)
          }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.text = name
    }

}

