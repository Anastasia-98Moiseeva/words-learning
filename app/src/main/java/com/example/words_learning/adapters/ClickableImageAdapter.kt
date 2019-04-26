package com.example.words_learning.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.words_learning.R

class ImageListAdapter(private val names : Array<String>, private val onClick : (Int) -> Unit) : RecyclerView.Adapter<ButtonHolde>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ButtonHolde {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.test_image_button_layout, viewGroup, false)
        val button : Button = rootView.findViewById(R.id.button)
        return ButtonHolde(rootView, button, onClick)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ButtonHolde, pos: Int) {
        holder.button.text = names[pos]
        holder.button.textSize = 12F
    }

}

class ButtonHolde(view : View, val button : Button, val onClick : (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        button.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}