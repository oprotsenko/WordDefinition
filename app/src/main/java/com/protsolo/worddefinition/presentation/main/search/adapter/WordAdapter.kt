package com.protsolo.worddefinition.presentation.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.protsolo.worddefinition.R
import com.protsolo.worddefinition.databinding.ItemWordBinding
import com.protsolo.worddefinition.presentation.main.search.IItemClickListener

class WordAdapter(private val onItemClickListener: IItemClickListener) : ListAdapter<String, WordViewHolder>(
    WordDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word, parent, false)
        return WordViewHolder(ItemWordBinding.bind(view))
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(position)
            }
        }
    }
}