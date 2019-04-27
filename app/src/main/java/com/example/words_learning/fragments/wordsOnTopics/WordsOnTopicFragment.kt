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
import ru.mail.technotrack.recyclerview.adapters.ClickableButtonAdapter

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

    val arrayOfImages : Array<Int> = arrayOf(
        R.drawable.ic_vegetable1,
        R.drawable.ic_cutlery1,
        R.drawable.ic_fruit1,
        R.drawable.ic_family1,
        R.drawable.ic_flower1,
        R.drawable.ic_footwear1,
        R.drawable.ic_animal1,
        R.drawable.ic_clothes1,
        R.drawable.ic_face1,
        R.drawable.ic_home1,
        R.drawable.ic_berry1,
        R.drawable.ic_furniture1,
        R.drawable.ic_profession1,
        R.drawable.ic_stationery1,
        R.drawable.ic_body1

    )

    val textSize : Float = 12F

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
        recycler.adapter = ClickableButtonAdapter(createButtons(),
            R.layout.words_image_button_layout, textSize,
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

