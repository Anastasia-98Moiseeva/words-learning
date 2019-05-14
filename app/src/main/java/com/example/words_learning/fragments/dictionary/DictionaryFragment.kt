package com.example.words_learning.fragments.dictionary

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import com.example.words_learning.adapters.DictionaryWords
import com.example.words_learning.database.Model
import com.example.words_learning.database.Words

class DictionaryFragment : Fragment() {

    private lateinit var model: Model
    private var wordsTranslation = ArrayList<Words>()
    private lateinit var router : Router
    val name = "Words On Topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)
        model = Model(activity!!)
        if (model.select() != null) {
            wordsTranslation = model.select()!!
        }
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

        if (wordsTranslation.size > 0) {
            recycler.adapter = DeletedListAdapter(layout, model, wordsTranslation)
        }

        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }


    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

    private fun getWords() {
        var db: SQLiteDatabase


    }

    companion object {
        private val words: ArrayList<DictionaryWords>
            get() =
                arrayListOf(
                    DictionaryWords("Prose", "Проза"),
                    DictionaryWords("Tulip", "Тюльпан"),
                    DictionaryWords("Dew", "Роса"),
                    DictionaryWords("Luxury", "Роскошь"),
                    DictionaryWords("Baroque", "Барокко"),
                    DictionaryWords("Introvert", "Интроверт"),
                    DictionaryWords("Borsch", "Борщ"),
                    DictionaryWords("Money", "Деньги"),
                    DictionaryWords("Science", "Наука"),
                    DictionaryWords("Angel", "Ангел"),
                    DictionaryWords("Laptop", "Ноутбук"),
                    DictionaryWords("Vanity", "Тщеславие"),
                    DictionaryWords("Football", "Футбол"),
                    DictionaryWords("Marvel", "Чудо"),
                    DictionaryWords("Nature", "Природа"),
                    DictionaryWords("Hoverboard", "Гироскутер")
                )
    }
}

