package com.protsolo.worddefinition.domain.useCases

import com.protsolo.worddefinition.data.DefinitionRepository
import com.protsolo.worddefinition.data.local.entity.DefinitionEntity

class RecentDefinition(private val definitionRepository: DefinitionRepository) {
    suspend fun getRecentWords() : List<DefinitionEntity>? =
        definitionRepository.getLocalWordsBase()

    suspend fun getLocalDataWord(word: String) =
        definitionRepository.getLocalWord(word)

    suspend fun clearData() {
        definitionRepository.clearData()
    }
}