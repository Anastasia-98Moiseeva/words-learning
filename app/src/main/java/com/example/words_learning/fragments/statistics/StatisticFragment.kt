package com.example.words_learning.fragments.statistics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.database.Model
import kotlinx.android.synthetic.main.fragment_statistic.view.*


class StatisticFragment : Fragment() {

    private lateinit var router : Router
    private lateinit var model: Model

    val name = "Statistics"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = Model(activity!!)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_statistic, container, false)

        layout = createButtons(layout)



        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout

    }

    private fun createButtons(layout: View) : View{


        val listView : ListView = layout.list
        listView.adapter = model.createAdapter()
        //listView.onItemClickListener = this

        //findViewById<View>(R.id.add).setOnClickListener(this)
        //findViewById<View>(R.id.delete).setOnClickListener(this)

//        layout.add.setOnClickListener(addNewValue())

        return layout
    }
/*
    private fun addNewValue() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(resources.getString(R.string.enter_text))

        val l = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = l.inflate(R.layout.edit, null)
        val text = v.findViewById<View>(R.id.edit_text) as EditText
        alertDialog.setView(v)
        text.setText("Hello, World!")

        alertDialog.setPositiveButton(R.string.ok) { dialog, which ->
            val value = text.text.toString()
            model.addValue(value)
        }

        alertDialog.show()
    }
*/
    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

}

