package com.protsolo.worddefinition.data.repository.remote.retrofit

import com.protsolo.worddefinition.domain.model.WordDefinition
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getDefinition(word: String): Response<WordDefinition>
}