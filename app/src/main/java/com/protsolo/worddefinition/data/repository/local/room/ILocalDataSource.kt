package com.protsolo.worddefinition.data.repository.local.room

import com.protsolo.worddefinition.domain.model.WordDefinition
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import retrofit2.Response

interface ILocalDataSource {
    suspend fun saveWordDefinition(response: Response<WordDefinition>)
    suspend fun getWordDefinition(word: String): WordDefinitionItem?
}