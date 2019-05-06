package com.example.words_learning.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.fragments.search.HomeFeed
import kotlinx.android.synthetic.main.clickable_item.view.*
import java.util.ArrayList


class SearchAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<SearchAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dictionary, null)
        return SearchAdapter.MyHolder(v)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val text = homeFeed.text.get(position)
        holder?.nameTxt.setText(text)
    }

    override fun getItemCount(): Int {
        return homeFeed.text.count()
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