package com.protsolo.worddefinition.data.repository.remote.retrofit

import com.protsolo.worddefinition.domain.model.WordDefinition
import com.protsolo.worddefinition.domain.repository.IRequestApi
import retrofit2.Response

class RetrofitDefinitionDataSource(private val requestApi: IRequestApi) : IRemoteDataSource {
    override suspend fun getDefinition(word: String): Response<WordDefinition> =
        requestApi.getDefinition(word)
}