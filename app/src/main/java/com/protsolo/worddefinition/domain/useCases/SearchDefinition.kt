package com.protsolo.worddefinition.domain.useCases

import com.protsolo.worddefinition.data.IRepository
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import retrofit2.Response

class SearchDefinition(private val repository: IRepository) {
    suspend fun getDefinition(word: String) : Response<WordDefinitionItem> =
        repository.getDefinition(word)
}