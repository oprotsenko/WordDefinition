package com.protsolo.worddefinition.domain.useCases

import com.protsolo.worddefinition.data.IRepository
import com.protsolo.worddefinition.data.local.entity.DefinitionEntity

class GetLocalDefinition(private val repository: IRepository) {
    suspend fun getRecentWords() : List<DefinitionEntity>? =
        repository.getLocalWordsBase()
    suspend fun getLocalDataWord(word: String) =
        repository.getLocalWord(word)
}