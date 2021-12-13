package com.protsolo.worddefinition.presentation.main.definition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.protsolo.worddefinition.domain.model.Meaning
import com.protsolo.worddefinition.domain.model.WordDefinitionItem

class DefinitionViewModel : ViewModel() {

    val definitionData by lazy { MutableLiveData<WordDefinitionItem>() }
    val meaningsData by lazy { MutableLiveData<List<Meaning>>() }

    fun setDefinitionItem(wordDefinitionItem: WordDefinitionItem) {
        definitionData.value = wordDefinitionItem
    }

    fun setMeanings(meanings: List<Meaning>?) {
        meaningsData.value = meanings
    }
}