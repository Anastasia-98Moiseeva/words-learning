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
import com.example.words_learning.adapters.CheckedListAdapter
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
        recycler.adapter = CheckedListAdapter(teachers)

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
                    SpiritualTeacher("Rumi", "Out beyond ideas of wrongdoing and rightdoing there is a field.I'll meet you there.", R.drawable.icon_loppa),
                    SpiritualTeacher("Anthony De Mello", "Don't Carry Over Experiences from the past", R.drawable.icon_loppa),
                    SpiritualTeacher("Eckhart Tolle", "Walk as if you are kissing the Earth with your feet.", R.drawable.icon_loppa),
                    SpiritualTeacher("Meister Eckhart", "Man suffers only because he takes seriously what the gods made for fun.", R.drawable.icon_loppa),
                    SpiritualTeacher("Mooji", "I have lived with several Zen masters -- all of them cats.", R.drawable.icon_loppa),
                    SpiritualTeacher("Confucius", "I'm simply saying that there is a way to be sane. I'm saying that you ", R.drawable.icon_loppa),
                    SpiritualTeacher("Francis Lucille", "The way out is through the door. Why is it that no one will use this method?", R.drawable.icon_loppa),
                    SpiritualTeacher("Thich Nhat Hanh", "t is the power of the mind to be unconquerable.", R.drawable.icon_loppa),
                    SpiritualTeacher("Dalai Lama", "It's like you took a bottle of ink and you threw it at a wall. Smash! ", R.drawable.icon_loppa),
                    SpiritualTeacher("Jiddu Krishnamurti", "A student, filled with emotion and crying, implored, 'Why is there so much suffering?", R.drawable.icon_loppa),
                    SpiritualTeacher("Osho", "Only the hand that erases can write the true thing.", R.drawable.icon_loppa),
                    SpiritualTeacher("Sedata", "Many have died; you also will die. The drum of death is being beaten.", R.drawable.icon_loppa),
                    SpiritualTeacher("Allan Watts", "Where there are humans, You'll find flies,And Buddhas.", R.drawable.icon_loppa),
                    SpiritualTeacher("Leo Gura", "Silence is the language of Om. We need silence to be able to reach our Self.", R.drawable.icon_loppa),
                    SpiritualTeacher("Rupert Spira", "One day in my shoes and a day for me in your shoes, the beauty of travel lies ", R.drawable.icon_loppa),
                    SpiritualTeacher("Sadhguru", "Like vanishing dew,a passing apparition or the sudden flashnof lightning", R.drawable.icon_loppa)
                )
    }
}

