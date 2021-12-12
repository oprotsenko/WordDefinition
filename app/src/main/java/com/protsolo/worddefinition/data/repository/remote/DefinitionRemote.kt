package com.protsolo.worddefinition.data.repository.remote

import com.protsolo.worddefinition.domain.repository.IRequestApi

class DefinitionRemote(private val requestApi: IRequestApi) {
    suspend fun getDefinition(word: String) = requestApi.getDefinition(word)
}