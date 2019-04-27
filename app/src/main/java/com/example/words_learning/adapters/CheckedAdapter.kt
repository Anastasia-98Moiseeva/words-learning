package com.example.words_learning.adapters


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.words_learning.R
import java.util.ArrayList


class SpiritualTeacher(var name: String?, val quote: String, val image: Int) {
    var isSelected: Boolean = false
}


class CheckedListAdapter(var teachers: ArrayList<SpiritualTeacher>) : RecyclerView.Adapter<CheckedListAdapter.MyHolder>() {
    var checkedTeachers = ArrayList<SpiritualTeacher>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.selected_item, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val teacher = teachers[position]
        holder.nameTxt.text = teacher.name
        holder.posTxt.text = teacher.quote
        holder.myCheckBox.isChecked = teacher.isSelected
        holder.myCheckBox.setText("")


        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentTeacher = teachers[pos]

                if (myCheckBox.isChecked) {
                    myCheckBox.setText("added")
                    currentTeacher.isSelected = true
                    checkedTeachers.add(currentTeacher)


                } else if (!myCheckBox.isChecked) {
                    currentTeacher.isSelected = false
                    checkedTeachers.remove(currentTeacher)
                    myCheckBox.setText("removed")
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return teachers.size
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