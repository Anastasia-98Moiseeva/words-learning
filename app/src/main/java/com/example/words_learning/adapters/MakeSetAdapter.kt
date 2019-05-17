package com.example.words_learning.adapters



import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.words_learning.R
import java.util.ArrayList



class MakeSetAdapter(var checkedTeachers: ArrayList<SpiritualTeacher>, var wordsBase: ArrayList<SpiritualTeacher>) : RecyclerView.Adapter<MakeSetAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.selected_item, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val word = wordsBase[position]
        holder.nameTxt.text = word.name
        holder.posTxt.text = word.quote
        holder.myCheckBox.isChecked = word.isSelected

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentTeacher = wordsBase[pos]

                if (myCheckBox.isChecked) {
                    currentTeacher.isSelected = true
                    checkedTeachers.add(currentTeacher)

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