package com.protsolo.worddefinition.presentation.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.useCases.SearchUseCase
import com.protsolo.worddefinition.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    val data: MutableLiveData<WordDefinitionItem> by lazy { MutableLiveData() }
    val definitionIsNotFound: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }

    fun fetchData(word: String) {
        viewModelScope.launch {
            val definition = searchUseCase.getDefinition(word)
            if (definition.isSuccessful) {
                data.postValue(definition.body())
            } else {
                definitionIsNotFound.postValue(true)
            }
        }
    }
}