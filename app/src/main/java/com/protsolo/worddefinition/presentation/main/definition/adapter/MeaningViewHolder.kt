package com.protsolo.worddefinition.presentation.main.definition.adapter

import androidx.recyclerview.widget.RecyclerView
import com.protsolo.worddefinition.R
import com.protsolo.worddefinition.databinding.ItemMeaningBinding
import com.protsolo.worddefinition.domain.model.Meaning

class MeaningViewHolder(private val binding: ItemMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(meaning: Meaning) {
        binding.apply {
            textViewPartOfSpeech.text = root.context.getString(R.string.part_of_speech)
                .plus(meaning.partOfSpeech)

            textViewDefinition.text = root.context.getString(R.string.definition)
                    .plus(meaning.definitions?.first()?.definition)
        }
    }
}