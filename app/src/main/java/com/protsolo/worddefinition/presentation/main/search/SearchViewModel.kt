package com.protsolo.worddefinition.presentation.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.protsolo.worddefinition.data.repository.DefinitionRepository
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchViewModel(private val definitionRepository: DefinitionRepository) : ViewModel() {

    val data: MutableLiveData<WordDefinitionItem> by lazy { MutableLiveData() }
    val definitionIsNotFound: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent() }

    private var job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun fetchData(word: String) {
        makeRequest(word)
    }

    private fun makeRequest(word: String) {
        CoroutineScope(coroutineContext).launch {
            val definition = definitionRepository.getDefinition(word)
            if (definition.isSuccessful) {
                data.postValue(definition.body())
            } else {
                definitionIsNotFound.postValue(true)
            }
        }
    }
}