package com.example.words_learning.adapters

import android.graphics.Color
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.database.dictionary.Dictionary
import com.example.words_learning.database.dictionary.Words
import com.example.words_learning.database.statistic.Statistic
import java.util.ArrayList



class DeletedListAdapter(val layout: View, val model1: Dictionary, var words: ArrayList<Words>, val statistic: Statistic) : RecyclerView.Adapter<DeletedListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.deleted_item, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val word = words[position]
        holder.nameTxt.text = word.word
        holder.posTxt.text = word.traslation


        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val currentTeacher = words[pos]

                model1.remove(words[pos])
                words.remove(currentTeacher)
                notifyDataSetChanged()
                statistic.addValue(words[pos].word)

                val snack = Snackbar.make(
                    layout,
                    currentTeacher.word + " has deleted",
                    Snackbar.LENGTH_LONG //
                )

                snack.view.setBackgroundColor(Color.parseColor("#310DD6"))

                snack.setAction(
                    "cancel deletion"
                ) {
                    model1.addValue(currentTeacher)
                    words.add(currentTeacher)
                    notifyDataSetChanged()
                }.show()




            }
        })
    }

    override fun getItemCount(): Int {
        return words.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView
        var posTxt: TextView
        var img: ImageButton

        lateinit var myItemClickListener: ItemClickListener

        init {

            nameTxt = itemView.findViewById(R.id.wordTextView)
            posTxt = itemView.findViewById(R.id.translationTextView)
            img = itemView.findViewById(R.id.deleteBox)
            img.setOnClickListener(this)
        }

        fun setItemClickListener(ic: ItemClickListener) {
            this.myItemClickListener = ic
        }

        override fun onClick(v: View) {
            this.myItemClickListener.onItemClick(v, layoutPosition)
        }

        interface ItemClickListener {

            fun onItemClick(v: View, pos: Int)
        }
    }

}