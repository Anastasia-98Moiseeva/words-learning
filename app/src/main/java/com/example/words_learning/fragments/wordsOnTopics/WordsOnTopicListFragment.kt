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
import com.example.words_learning.database.dictionary.Dictionary
import com.example.words_learning.database.dictionary.Words
import com.example.words_learning.massege


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class WordsOnTopicListFragment : Fragment() {

    private lateinit var model : Dictionary
    private lateinit var router : Router
    private var words : ArrayList<SpiritualTeacher> = arrayListOf()
    val name = "Words On Topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
        model = Dictionary(activity!!)

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

        val idTopic = this.arguments!!.getInt(massege, 0)


        val wordsAndTranslation = (resources.getStringArray(idTopic))



        words.clear()

        for (wordTranslation in wordsAndTranslation) {

            val listWords = wordTranslation.split(",")
            val word = SpiritualTeacher(-1, listWords[0], listWords[1])

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

    private fun parse(word : String) {

    }

}

