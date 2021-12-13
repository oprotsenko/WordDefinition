package com.protsolo.worddefinition.data

import com.protsolo.worddefinition.data.local.entity.DefinitionEntity
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import retrofit2.Response

interface IRepository {
    suspend fun getDefinition(word: String): Response<WordDefinitionItem>
    suspend fun getLocalWordsBase() : List<DefinitionEntity>?
    suspend fun getLocalWord(word: String) : WordDefinitionItem?
    suspend fun clearData()
}