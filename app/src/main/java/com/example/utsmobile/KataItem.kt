package com.example.utsmobile

import androidx.recyclerview.widget.DiffUtil

sealed class KataItem {
    data class Header(val title: String) : KataItem()
    data class KataData(val kata: Kata) : KataItem()
}

class KataItemDiffCallback : DiffUtil.ItemCallback<KataItem>() {
    override fun areItemsTheSame(oldItem: KataItem, newItem: KataItem): Boolean {
        return when {
            oldItem is KataItem.Header && newItem is KataItem.Header -> oldItem.title == newItem.title
            oldItem is KataItem.KataData && newItem is KataItem.KataData -> oldItem.kata.id == newItem.kata.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: KataItem, newItem: KataItem): Boolean {
        return oldItem == newItem
    }
}

