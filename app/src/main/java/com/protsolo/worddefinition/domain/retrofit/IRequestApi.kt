package com.protsolo.worddefinition.domain.retrofit

import com.protsolo.worddefinition.domain.model.WordDefinition
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface IRequestApi {
    @GET("{word}")
    suspend fun getDefinition(@Path("word") word: String) : Response<WordDefinition>
}