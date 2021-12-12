package com.protsolo.worddefinition.domain.repository.local

import com.protsolo.worddefinition.data.local.entity.DefinitionEntity
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import retrofit2.Response

interface ILocalDataSource {
    suspend fun saveWordDefinition(response: Response<WordDefinitionModel>)
    suspend fun getWordDefinition(word: String): WordDefinitionItem?
    suspend fun getLocalWordsBase(): List<DefinitionEntity>?
    suspend fun clearData()
}