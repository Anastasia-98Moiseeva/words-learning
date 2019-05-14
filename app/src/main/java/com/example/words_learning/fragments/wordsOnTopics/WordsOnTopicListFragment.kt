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
import com.example.words_learning.adapters.CheckedListAdapter
import com.example.words_learning.adapters.SpiritualTeacher
import com.example.words_learning.database.Model
import com.example.words_learning.database.Words

class WordsOnTopicListFragment : Fragment() {

    private lateinit var model : Model
    private lateinit var router : Router
    private var words : ArrayList<SpiritualTeacher> = arrayListOf()
    val name = "Words On Topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
        model = Model(activity!!)

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_list, container, false)
        val recycler : RecyclerView = layout.findViewById(R.id.list)

        recycler.setHasFixedSize(true)

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            RecyclerView.VERTICAL,
            false
        )



        words.clear()

        for (word in teachers) {
            if (!model.findWord(Words(-1, word.name, word.quote))) {
                    words.add(word)
                }
        }

        recycler.adapter = CheckedListAdapter(model, words)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun onButtonClick(position: Int) {
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }


    companion object {
        private val teachers: ArrayList<SpiritualTeacher>
            get() =
                arrayListOf(
                    SpiritualTeacher(-1, "Prose", "Проза"),
                    SpiritualTeacher(-1, "Tulip", "Тюльпан"),
                    SpiritualTeacher(-1, "Dew", "Роса"),
                    SpiritualTeacher(-1, "Luxury", "Роскошь"),
                    SpiritualTeacher(-1, "Baroque", "Барокко"),
                    SpiritualTeacher(-1, "Introvert", "Интроверт"),
                    SpiritualTeacher(-1, "Borsch", "Борщ"),
                    SpiritualTeacher(-1, "Money", "Деньги"),
                    SpiritualTeacher(-1, "Science", "Наука"),
                    SpiritualTeacher(-1, "Angel", "Ангел"),
                    SpiritualTeacher(-1, "Laptop", "Ноутбук"),
                    SpiritualTeacher(-1, "Vanity", "Тщеславие"),
                    SpiritualTeacher(-1, "Football", "Футбол"),
                    SpiritualTeacher(-1, "Marvel", "Чудо"),
                    SpiritualTeacher(-1, "Nature", "Природа"),
                    SpiritualTeacher(-1, "Hoverboard", "Гироскутер")
                )
    }
}

