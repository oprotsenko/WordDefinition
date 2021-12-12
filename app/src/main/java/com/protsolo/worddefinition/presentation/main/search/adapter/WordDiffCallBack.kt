package com.protsolo.worddefinition.presentation.main.search.adapter

import androidx.recyclerview.widget.DiffUtil

class WordDiffCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) =
        oldItem.length == newItem.length

    override fun areContentsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem
}