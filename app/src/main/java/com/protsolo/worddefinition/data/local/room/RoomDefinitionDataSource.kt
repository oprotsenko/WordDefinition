package com.protsolo.worddefinition.data.local.room

import com.google.gson.Gson
import com.protsolo.worddefinition.data.local.entity.DefinitionEntity
import com.protsolo.worddefinition.data.local.entity.mapToRemote
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import com.protsolo.worddefinition.domain.repository.local.ILocalDataSource
import retrofit2.Response
class RoomDefinitionDataSource(
    private val gson: Gson,
    private val wordDefinitionDao: WordDefinitionDao
) : ILocalDataSource {

    override suspend fun saveWordDefinition(response: Response<WordDefinitionModel>) {
        val definitionEntity = response.body()?.map {
            DefinitionEntity(
                word = it.word,
                phonetic = it.phonetic,
                meanings = gson.toJson(it.meanings)
            )
        }
        definitionEntity?.first()?.let { wordDefinitionDao.addDefinition(definitionEntity = it) }
    }

    override suspend fun getWordDefinition(word: String): WordDefinitionItem? =
        wordDefinitionDao.getDefinition(word)?.mapToRemote(gson)
}

