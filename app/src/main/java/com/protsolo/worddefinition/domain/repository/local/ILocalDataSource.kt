package com.protsolo.worddefinition.domain.repository.local

import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import retrofit2.Response

interface ILocalDataSource {
    suspend fun saveWordDefinition(response: Response<WordDefinitionModel>)
    suspend fun getWordDefinition(word: String): WordDefinitionItem?
}