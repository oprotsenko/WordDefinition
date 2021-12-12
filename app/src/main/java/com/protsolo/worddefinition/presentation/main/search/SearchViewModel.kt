package com.protsolo.worddefinition.presentation.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.useCases.RecentDefinition
import com.protsolo.worddefinition.domain.useCases.SearchDefinition
import com.protsolo.worddefinition.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchDefinition: SearchDefinition,
    private val recentDefinition: RecentDefinition
) : ViewModel() {

    val recentWordsData by lazy { MutableLiveData<List<String>>() }
    val wordDefinition by lazy { MutableLiveData<WordDefinitionItem>() }
    val definitionIsNotFound by lazy { SingleLiveEvent<Boolean>() }
    val internetAccess by lazy { SingleLiveEvent<Boolean>() }

    init {
        viewModelScope.launch {
            refreshRecentWords()
        }
    }

    fun fetchData(word: String) {
        viewModelScope.launch {
            val definition = searchDefinition.getDefinition(word)
            if (definition.isSuccessful) {
                wordDefinition.postValue(definition.body())
            } else {
                when (definition.code()) {
                    401 -> internetAccess.postValue(false)
                    else -> definitionIsNotFound.postValue(true)
                }

            }
            refreshRecentWords()
        }
    }

    suspend fun clearRecentWords() {
        recentDefinition.clearData()
        refreshRecentWords()
    }

    suspend fun getLocalWord(word: String) =
        recentDefinition.getLocalDataWord(word)

    private suspend fun refreshRecentWords() {
        val wordsList = recentDefinition.getRecentWords()?.map {
            it.word
        }
        recentWordsData.value = wordsList
    }
}