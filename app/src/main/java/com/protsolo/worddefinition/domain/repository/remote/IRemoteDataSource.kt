package com.protsolo.worddefinition.domain.repository.remote

import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getDefinition(word: String): Response<WordDefinitionModel>
}