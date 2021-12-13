package com.protsolo.worddefinition.presentation.main.definition.adapter

import androidx.recyclerview.widget.DiffUtil
import com.protsolo.worddefinition.domain.model.Meaning

class MeaningDiffCallBack : DiffUtil.ItemCallback<Meaning>() {
    override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning) =
        oldItem.partOfSpeech == newItem.partOfSpeech

    override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning) =
        oldItem == newItem
}