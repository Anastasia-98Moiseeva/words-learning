package com.example.words_learning.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.database.dictionary.Dictionary
import com.example.words_learning.database.dictionary.Words
import java.util.ArrayList


class SpiritualTeacher(var id: Int, var name: String, val quote: String) {
    var isSelected: Boolean = false
}


class CheckedListAdapter(val model : Dictionary, var wordsBase: ArrayList<SpiritualTeacher>) : RecyclerView.Adapter<CheckedListAdapter.MyHolder>() {
    var checkedTeachers = ArrayList<SpiritualTeacher>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.selected_item, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val teacher = wordsBase[position]
        holder.nameTxt.text = teacher.name
        holder.posTxt.text = teacher.quote
        holder.myCheckBox.isChecked = teacher.isSelected

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentTeacher = wordsBase[pos]

                if (myCheckBox.isChecked) {
                    currentTeacher.isSelected = true
                    model.addValue(
                        Words(

                            -1,
                            currentTeacher.name,
                            currentTeacher.quote
                        )
                    )
                    checkedTeachers.add(currentTeacher)


                } else if (!myCheckBox.isChecked) {
                    currentTeacher.isSelected = false
                    model.removeByWord(
                        Words(
                            -1,
                            currentTeacher.name,
                            currentTeacher.quote
                        )
                    )
                    checkedTeachers.remove(currentTeacher)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return wordsBase.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView
        var posTxt: TextView
        var myCheckBox: CheckBox

        lateinit var myItemClickListener: ItemClickListener

        init {

            nameTxt = itemView.findViewById(R.id.wordTextView)
            posTxt = itemView.findViewById(R.id.translationTextView)
            myCheckBox = itemView.findViewById(R.id.myCheckBox)
            myCheckBox.setOnClickListener(this)
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