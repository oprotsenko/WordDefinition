package com.protsolo.worddefinition.presentation.main.definition

import androidx.fragment.app.viewModels
import com.protsolo.worddefinition.R
import com.protsolo.worddefinition.base.BaseFragment
import com.protsolo.worddefinition.databinding.FragmentDefinitionBinding
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.presentation.main.definition.adapter.MeaningsAdapter
import com.protsolo.worddefinition.utils.extentions.hideKeyboard

class DefinitionFragment :
    BaseFragment<FragmentDefinitionBinding>(FragmentDefinitionBinding::inflate) {

    private val viewModel: DefinitionViewModel by viewModels()
    private val meaningsAdapter by lazy { MeaningsAdapter() }

    override fun recyclerInit() {
        binding.recyclerViewMeanings.apply {
            adapter = meaningsAdapter
            itemAnimator?.changeDuration = 0
        }
    }

    override fun setObservers() {
        viewModel.apply {
            definitionData.observe(viewLifecycleOwner, {
                binding.apply {
                    textViewWord.text = requireContext().getString(R.string.word).plus(it.word)
                    textViewPhonetic.text = requireContext().getString(R.string.phonetic).plus(it.phonetic)
                    textViewMeanings.text = requireContext().getString(R.string.meanings)
                    setMeanings(it.meanings)
                }
            })

            meaningsData.observe(viewLifecycleOwner, {
                meaningsAdapter.submitList(it)
            })
        }
    }

    override fun setListeners() {
        binding.root.apply {
            setOnClickListener {
                hideKeyboard()
            }
        }
    }

    fun setDefinition(wordDefinitionItem: WordDefinitionItem) {
        viewModel.setDefinitionItem(wordDefinitionItem)
    }
}