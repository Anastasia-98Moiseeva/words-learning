package com.example.words_learning.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import java.util.ArrayList


class SpiritTeacher(var name: String?) {
    var isSelected: Boolean = false
}


class LearnSetAdapter(var teachers: ArrayList<SpiritTeacher>) : RecyclerView.Adapter<LearnSetAdapter.MyHolder>() {
    var checkedTeachers = ArrayList<SpiritTeacher>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_choose_set, null)
        return MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val teacher = teachers[position]
        holder.nameTxt.text = teacher.name
        holder.myCheckBox.isChecked = teacher.isSelected

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val myCheckBox = v as CheckBox
                val currentTeacher = teachers[pos]

                if (myCheckBox.isChecked) {
                    currentTeacher.isSelected = true
                    checkedTeachers.add(currentTeacher)


                } else if (!myCheckBox.isChecked) {
                    currentTeacher.isSelected = false
                    checkedTeachers.remove(currentTeacher)
                }
            }
        })

//        holder.setItemImageButtonClickListener(object : MyHolder.ItemClickListener {
//            override fun onItemClick(v: View, pos: Int) {

//            }
//        })
    }

    override fun getItemCount(): Int {
        return teachers.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView
        var myCheckBox: CheckBox
        var imgBut: ImageButton

        lateinit var myItemClickListener: ItemClickListener
        lateinit var myImageButtonClickListener: ItemClickListener

        init {

            nameTxt = itemView.findViewById(R.id.set)
            imgBut = itemView.findViewById(R.id.imageButton)
            myCheckBox = itemView.findViewById(R.id.myCheckBox)
            myCheckBox.setOnClickListener(this)
            imgBut.setOnClickListener {nameTxt.setText("hm")}
        }

        fun setItemImageButtonClickListener() {

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