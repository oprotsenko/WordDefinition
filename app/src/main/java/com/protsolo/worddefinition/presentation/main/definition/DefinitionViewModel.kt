package com.protsolo.worddefinition.presentation.main.definition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.protsolo.worddefinition.domain.model.WordDefinitionItem

class DefinitionViewModel : ViewModel() {

    val definitionData: MutableLiveData<WordDefinitionItem> by lazy { MutableLiveData() }

    fun getDefinitionItem(wordDefinitionItem: WordDefinitionItem) {
        definitionData.value = wordDefinitionItem
    }
}