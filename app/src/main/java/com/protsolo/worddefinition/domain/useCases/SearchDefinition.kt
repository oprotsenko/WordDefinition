package com.protsolo.worddefinition.domain.useCases

import com.protsolo.worddefinition.data.DefinitionRepository
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import retrofit2.Response

class SearchDefinition(private val definitionRepository: DefinitionRepository) {
    suspend fun getDefinition(word: String) : Response<WordDefinitionItem> =
        definitionRepository.getDefinition(word)
}