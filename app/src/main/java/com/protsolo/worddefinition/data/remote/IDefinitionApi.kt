package com.protsolo.worddefinition.data.remote

import com.protsolo.worddefinition.domain.model.WordDefinitionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IDefinitionApi {
    @GET("{word}")
    suspend fun getDefinition(@Path("word") word: String) : Response<WordDefinitionModel>
}