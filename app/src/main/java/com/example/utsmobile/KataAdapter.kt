package com.example.utsmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobile.databinding.ItemKataBinding

class KataAdapter(private val kataList: List<Kata>) : RecyclerView.Adapter<KataAdapter.KataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KataViewHolder {
        val binding = ItemKataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KataViewHolder, position: Int) {
        val kata = kataList[position]
        holder.bind(kata)
    }

    override fun getItemCount(): Int {
        return kataList.size
    }

    class KataViewHolder(private val binding: ItemKataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kata: Kata) {
            binding.textKata.text = kata.kata
            binding.textArti.text = kata.arti
            binding.textKategori.text = kata.kategori
        }
    }
}
