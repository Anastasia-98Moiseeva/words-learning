package com.example.words_learning.fragments.dictionary

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
import com.example.words_learning.adapters.SpiritualTeacher

class DictionaryFragment : Fragment() {

    private lateinit var router : Router
    val name = "Words On Topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

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
        recycler.adapter = DeletedListAdapter(teachers)


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
                    SpiritualTeacher("Girl", "Девушка", R.drawable.icon_loppa),
                    SpiritualTeacher("Wine", "Вино", R.drawable.icon_loppa),
                    SpiritualTeacher("Borsch", "Борщ", R.drawable.icon_loppa),
                    SpiritualTeacher("Debauchery", "Разврат", R.drawable.icon_loppa),
                    SpiritualTeacher("Money", "Деньги", R.drawable.icon_loppa),
                    SpiritualTeacher("Science", "Наука", R.drawable.icon_loppa),
                    SpiritualTeacher("Angel", "Ангел", R.drawable.icon_loppa),
                    SpiritualTeacher("Laptop", "Ноутбук", R.drawable.icon_loppa),
                    SpiritualTeacher("Vanity", "Тщеславие", R.drawable.icon_loppa),
                    SpiritualTeacher("Stripper", "Стриптизерша", R.drawable.icon_loppa),
                    SpiritualTeacher("Football", "Футбол", R.drawable.icon_loppa),
                    SpiritualTeacher("Santa Claus", "Дед Мороз", R.drawable.icon_loppa),
                    SpiritualTeacher("Marvel", "Чудо", R.drawable.icon_loppa),
                    SpiritualTeacher("Nature", "Природа", R.drawable.icon_loppa),
                    SpiritualTeacher("Love", "Любовь", R.drawable.icon_loppa),
                    SpiritualTeacher("Hoverboard", "Гироскутер", R.drawable.icon_loppa)
                )
    }
}

