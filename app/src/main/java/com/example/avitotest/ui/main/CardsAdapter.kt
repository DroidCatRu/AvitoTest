package com.example.avitotest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotest.databinding.CardBinding
import com.example.avitotest.model.CardData

class CardsAdapter(private val clickListener: DeleteButtonListener) : ListAdapter<CardData,
    CardsAdapter.ViewHolder>(CardsDiffCallback()) {

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(clickListener, getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent)
  }

  class ViewHolder private constructor(
      private val binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: DeleteButtonListener, item: CardData) {
      binding.data = item
      binding.clickListener = clickListener
      binding.executePendingBindings()
    }

    companion object {

      fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
      }
    }
  }
}

class CardsDiffCallback : DiffUtil.ItemCallback<CardData>() {

  override fun areItemsTheSame(oldItem: CardData, newItem: CardData): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CardData, newItem: CardData): Boolean {
    return oldItem == newItem
  }
}

class DeleteButtonListener(val clickListener: (id: String) -> Unit) {

  fun onClick(data: CardData) = clickListener(data.id)
}