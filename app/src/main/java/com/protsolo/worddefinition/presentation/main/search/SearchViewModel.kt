package com.protsolo.worddefinition.presentation.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.protsolo.worddefinition.data.Repository
import com.protsolo.worddefinition.domain.model.WordDefinition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchViewModel(private val repository: Repository) : ViewModel() {

    val data: MutableLiveData<WordDefinition> by lazy { MutableLiveData() }

    private var job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun fetchData(word: String) {
        CoroutineScope(coroutineContext).launch {
            val definition = repository.getDefinition(word)
            if (definition.isSuccessful) {
                Log.d("TAG", definition.body()?.word.toString())
                data.postValue(definition.body())
            } else {
                Log.d("TAG", definition.message())
            }
        }
    }

    private fun getDefinition(word: String): LiveData<WordDefinition> {
        return liveData {
            val definition = repository.getDefinition(word)
            if (definition.isSuccessful) {
                Log.d("TAG", definition.body()?.word.toString())
                definition.body()?.let { emit(it) }
            } else {
                Log.d("TAG", definition.message())
            }
        }
    }
}