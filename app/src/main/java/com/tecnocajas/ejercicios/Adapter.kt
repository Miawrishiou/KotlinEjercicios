package com.tecnocajas.ejercicios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val items: List<CardItem>, private val onItemClick: (CardItem) -> Unit) : RecyclerView.Adapter<Adapter.ExcerciseViewHolder>() {
    class ExcerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ID: TextView = view.findViewById(R.id.card_ID)
        val title: TextView = view.findViewById(R.id.card_title)
        val description: TextView = view.findViewById(R.id.card_description)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExcerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ExcerciseViewHolder(view)
    }
    override fun onBindViewHolder(holder: ExcerciseViewHolder, position: Int) {
        val item = items[position]
        holder.ID.text = item.ID.toString()
        holder.title.text = item.title
        holder.description.text = item.description

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
    override fun getItemCount() = items.size
}