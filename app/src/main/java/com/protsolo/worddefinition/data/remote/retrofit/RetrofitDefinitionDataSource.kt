package com.protsolo.worddefinition.data.remote.retrofit

import com.protsolo.worddefinition.data.remote.IDefinitionApi
import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import com.protsolo.worddefinition.domain.repository.remote.IRemoteDataSource
import retrofit2.Response

class RetrofitDefinitionDataSource(private val definitionApi: IDefinitionApi) : IRemoteDataSource {
    override suspend fun getDefinition(word: String): Response<WordDefinitionModel> =
        definitionApi.getDefinition(word)
}