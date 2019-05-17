package com.example.words_learning.fragments.makeSet

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
import com.example.words_learning.adapters.DeletedListAdapter
import com.example.words_learning.adapters.MakeSetAdapter
import com.example.words_learning.adapters.SpiritualTeacher
import com.example.words_learning.database.dictionarybase.Model
import com.example.words_learning.database.dictionarybase.Words


class MakeSetFragment : Fragment() {

    private lateinit var router : Router
    val name = "Make set"
    private var wordsAll : ArrayList<SpiritualTeacher> = arrayListOf()
    private  var checkedWords : ArrayList<SpiritualTeacher> = arrayListOf()

    private lateinit var model: Model
    private var wordsTranslation = ArrayList<Words>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

        model = Model(activity!!)
        if (model.select() != null) {
            wordsTranslation = model.select()!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragments_make_set, container, false)
        layout = createButtons(layout)

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        val recycler : RecyclerView = layout.findViewById(R.id.list1)

        recycler.setHasFixedSize(true)

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            RecyclerView.VERTICAL,
            false
        )

        for (word in wordsTranslation) {
            wordsAll.add(SpiritualTeacher(0, word.word, word.traslation ))
        }

        if (wordsTranslation.size > 0) {
            recycler.adapter = MakeSetAdapter(checkedWords, wordsAll)
        }

        return layout

    }


    private fun createButtons(layout: View) : View{
        return layout
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

}

