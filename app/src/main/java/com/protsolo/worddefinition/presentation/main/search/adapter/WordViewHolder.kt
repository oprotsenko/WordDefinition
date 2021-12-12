package com.protsolo.worddefinition.presentation.main.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.protsolo.worddefinition.databinding.ItemWordBinding

class WordViewHolder(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(word: String) {
        binding.textViewWord.text = word
    }
}