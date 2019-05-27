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



class DeletedListAdapter(val layout: View, val dictionary: Dictionary, val statistic: Statistic) : RecyclerView.Adapter<DeletedListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.deleted_item, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val word = dictionary.getElementPos(position)
        if (word != null) {
            holder.nameTxt.text = word.word
            holder.posTxt.text = word.traslation
        }

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val currentWord = dictionary.getElementPos(pos)

                if (currentWord != null) {
                    statistic.addWordInformation(currentWord.word)
                    dictionary.removeElementPos(pos)
                    //dictionary.remove(words[pos])
                    //words.remove(currentWord)
                    notifyDataSetChanged()


                    val snack = Snackbar.make(
                        layout,
                        currentWord.word + " has deleted",
                        Snackbar.LENGTH_LONG //
                    )

                    snack.view.setBackgroundColor(Color.parseColor("#7B338F"))

                    snack.setAction(
                        "cancel deletion"
                    ) {
                        dictionary.returnElement(currentWord)
                        //dictionary.addValue(currentWord)
                        //words.add(currentWord)
                        notifyDataSetChanged()
                    }.show()

                }


            }
        })
    }

    override fun getItemCount(): Int {
        return dictionary.sizeWordsTranslation()
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView = itemView.findViewById(R.id.wordTextView)
        var posTxt: TextView = itemView.findViewById(R.id.translationTextView)
        var img: ImageButton = itemView.findViewById(R.id.deleteBox)

        lateinit var myItemClickListener: ItemClickListener

        init {

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