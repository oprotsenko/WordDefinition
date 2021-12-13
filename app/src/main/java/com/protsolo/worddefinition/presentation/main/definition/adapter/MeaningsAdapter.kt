package com.protsolo.worddefinition.presentation.main.definition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.protsolo.worddefinition.R
import com.protsolo.worddefinition.databinding.ItemMeaningBinding
import com.protsolo.worddefinition.domain.model.Meaning

class MeaningsAdapter : ListAdapter<Meaning, MeaningViewHolder>(
    MeaningDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meaning, parent, false)
        return MeaningViewHolder(ItemMeaningBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
        }
    }
}