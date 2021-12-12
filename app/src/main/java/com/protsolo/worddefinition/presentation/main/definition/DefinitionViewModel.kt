package com.protsolo.worddefinition.presentation.main.definition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.protsolo.worddefinition.domain.model.WordDefinitionItem

class DefinitionViewModel : ViewModel() {

    val definitionData by lazy { MutableLiveData<WordDefinitionItem>() }

    fun getDefinitionItem(wordDefinitionItem: WordDefinitionItem) {
        definitionData.value = wordDefinitionItem
    }
}