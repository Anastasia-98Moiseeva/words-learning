package com.example.words_learning.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.view.LayoutInflater
import com.example.words_learning.R


class WordsAdapter(private val names : Array<String>, private val cur_layout: Int,
                             private val textSize : Float, private val arrayOfImages: Array<Pair<Int, Int>>,
                             private val isImageButton : Boolean,
                             private val onClick : (Int) -> Unit) : RecyclerView.Adapter<ButtonHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ButtonHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(cur_layout, viewGroup, false)
        val button : Button = rootView.findViewById(R.id.button)
        return ButtonHolder(rootView, button, onClick)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ButtonHolder, pos: Int) {
        holder.button.text = names[pos]
        holder.button.textSize = textSize
        if(isImageButton) {
            val img = arrayOfImages[pos].first
            holder.button.setCompoundDrawablesWithIntrinsicBounds( 0, img, 0, 0)
        }
    }

}
