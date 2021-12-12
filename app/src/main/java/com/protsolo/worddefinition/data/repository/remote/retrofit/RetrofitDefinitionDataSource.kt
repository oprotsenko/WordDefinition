package com.protsolo.worddefinition.data.repository.remote.retrofit

import com.protsolo.worddefinition.data.repository.remote.IRequestApi
import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import com.protsolo.worddefinition.domain.repository.remote.IRemoteDataSource
import retrofit2.Response

class RetrofitDefinitionDataSource(private val requestApi: IRequestApi) : IRemoteDataSource {
    override suspend fun getDefinition(word: String): Response<WordDefinitionModel> =
        requestApi.getDefinition(word)
}