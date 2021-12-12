package com.protsolo.worddefinition.presentation.main.definition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.protsolo.worddefinition.databinding.FragmentDefinitionBinding
import com.protsolo.worddefinition.domain.useCases.definition.DefinitionViewModel
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.presentation.base.BaseFragment


class DefinitionFragment :
    BaseFragment<FragmentDefinitionBinding>(FragmentDefinitionBinding::inflate) {

    private val viewModel: DefinitionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.definitionData.observe(viewLifecycleOwner, {
            binding.textViewDefinition.text = parseDefinition(it)
        })
    }

    fun setDefinition(wordDefinitionItem: WordDefinitionItem) {
        viewModel.getDefinitionItem(wordDefinitionItem)
    }

    private fun parseDefinition(wordDefinitionItem: WordDefinitionItem): StringBuilder {
        val definition = StringBuilder()
        definition.append("word: ")
            .append(wordDefinitionItem.word)
            .append("\n\n")
            .append("phonetic: ")
            .append(wordDefinitionItem.phonetic)
            .append("\n\nmeanings:")
        wordDefinitionItem.meanings?.forEach { meaning ->
            definition.append("\n       part of speech: ")
                .append(meaning.partOfSpeech)
                .append("\n       definition: ")
                .append(meaning.definitions?.first()?.definition)
                .append("\n")
        }
        return definition
    }
}