package com.example.dogbreedinfo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreedinfo.model.Breed

class BreedAdapter(private val onClick: (Breed) -> Unit) :
    ListAdapter<Breed, BreedAdapter.BreedViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BreedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(android.R.id.text1)
        fun bind(breed: Breed) {
            textView.text = breed.name
            itemView.setOnClickListener { onClick(breed) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Breed, newItem: Breed) = oldItem == newItem
    }
}