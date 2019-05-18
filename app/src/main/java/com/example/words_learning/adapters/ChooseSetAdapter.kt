package com.example.words_learning.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.database.set.Sets
import java.util.ArrayList



class ChooseSetAdapter(set1: Sets, var teachers: ArrayList<String>,
                       private val onClick : (Int) -> Unit) : RecyclerView.Adapter<ChooseSetAdapter.MyHolder>() {
    var checkedTeachers = ArrayList<String>()
    var set = set1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_choose_set, null)
        return MyHolder(set, teachers, v, onClick)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val teacher = teachers[position]
        holder.nameTxt.text = teacher

        holder.setItemClickListener(object : MyHolder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {

                //set.removeSet(teachers[pos])
                //teachers.removeAt(pos)
                //notifyDataSetChanged()
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

    class MyHolder(set : Sets, namesSets : ArrayList<String>, itemView: View, val onClick : (Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTxt: TextView
        var myCheckBox: CheckBox
        var imgBut: ImageButton

        lateinit var myItemClickListener: ItemClickListener
        lateinit var myImageButtonClickListener: ItemClickListener

        init {

            nameTxt = itemView.findViewById(R.id.set)
            imgBut = itemView.findViewById(R.id.imageButton)
            myCheckBox = itemView.findViewById(R.id.myCheckBox)
            myCheckBox.setOnClickListener{
                onClick(adapterPosition)
            }
            /*imgBut.setOnClickListener {
                set.removeSet(nameTxt.text.toString())
                namesSets.remove(nameTxt.text.toString())

            }*/
            imgBut.setOnClickListener(this)
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