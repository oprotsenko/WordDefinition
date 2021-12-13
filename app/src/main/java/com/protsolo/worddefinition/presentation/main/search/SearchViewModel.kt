package com.protsolo.worddefinition.presentation.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.useCases.ClearRecentWords
import com.protsolo.worddefinition.domain.useCases.GetLocalDefinition
import com.protsolo.worddefinition.domain.useCases.SearchDefinition
import com.protsolo.worddefinition.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchDefinition: SearchDefinition,
    private val getLocalDefinition: GetLocalDefinition,
    private val clearRecentWords: ClearRecentWords
) : ViewModel() {

    val recentWordsData by lazy { MutableLiveData<List<String>>() }
    val wordDefinitionData by lazy { MutableLiveData<WordDefinitionItem>() }
    val definitionIsNotFound by lazy { SingleLiveEvent<Boolean>() }
    val internetAccessStatement by lazy { SingleLiveEvent<Boolean>() }

    init {
        viewModelScope.launch {
            refreshRecentWords()
        }
    }

    fun fetchData(word: String) {
        viewModelScope.launch {
            val definition = searchDefinition.getDefinition(word)
            if (definition.isSuccessful) {
                wordDefinitionData.postValue(definition.body())
            } else {
                when (definition.code()) {
                    401 -> internetAccessStatement.postValue(false)
                    else -> definitionIsNotFound.postValue(true)
                }

            }
            refreshRecentWords()
        }
    }

    suspend fun clearRecentWords() {
        clearRecentWords.clearData()
        refreshRecentWords()
    }

    suspend fun getLocalWord(word: String) =
        getLocalDefinition.getLocalDataWord(word)

    private suspend fun refreshRecentWords() {
        val wordsList = getLocalDefinition.getRecentWords()?.map {
            it.word
        }
        recentWordsData.value = wordsList
    }
}