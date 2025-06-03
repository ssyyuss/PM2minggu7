package com.example.utsmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kata_table")
data class Kata(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val kata: String,
    val arti: String,
    val kategori: String
)

class KataListAdapter : ListAdapter<KataItem, RecyclerView.ViewHolder>(KataItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_heder, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kata, parent, false)
                KataViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val headerItem = getItem(position) as KataItem.Header
                holder.headerTextView.text = headerItem.title
            }
            is KataViewHolder -> {
                val kataItem = getItem(position) as KataItem.KataData
                holder.kataTextView.text = kataItem.kata.kata
                holder.artiTextView.text = kataItem.kata.arti
                holder.kategoriTextView.text = kataItem.kata.kategori
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is KataItem.Header -> 0
            is KataItem.KataData -> 1
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView = itemView.findViewById<TextView>(R.id.textHeader)
    }

    class KataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kataTextView = itemView.findViewById<TextView>(R.id.textKata)
        val artiTextView = itemView.findViewById<TextView>(R.id.textArti)
        val kategoriTextView = itemView.findViewById<TextView>(R.id.textKategori)
    }
}
