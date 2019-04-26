package ru.mail.technotrack.recyclerview.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.view.LayoutInflater
import com.example.words_learning.R
import kotlinx.android.synthetic.main.activity_main.view.*

class ClickableButtonAdapter(private val names : Array<String>, private val cur_layout: Int, private val textSize : Float,
                             private val onClick : (Int) -> Unit) : RecyclerView.Adapter<ButtonHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ButtonHolder {
        //val cur_layout = R.layout.button_layout
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
    }

}

class ButtonHolder(view : View, val button : Button, val onClick : (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        button.setOnClickListener {
            onClick(adapterPosition)
        }
    }
}