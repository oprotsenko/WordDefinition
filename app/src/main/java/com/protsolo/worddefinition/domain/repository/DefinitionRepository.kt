package com.protsolo.worddefinition.domain.repository

import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.domain.repository.local.ILocalDataSource
import com.protsolo.worddefinition.domain.repository.remote.IRemoteDataSource
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class DefinitionRepository(
    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: IRemoteDataSource
) {

    suspend fun getDefinition(word: String): Response<WordDefinitionItem> {
        val localDefinition = localDataSource.getWordDefinition(word)
        return if (localDefinition == null) {
            val remoteDefinition = remoteDataSource.getDefinition(word)
            localDataSource.saveWordDefinition(remoteDefinition)
            if (remoteDefinition.isSuccessful)
                Response.success(remoteDefinition.body()?.first())
            else {
                Response.error(400, "fail".toResponseBody())
            }
        } else {
            Response.success(localDefinition)
        }
    }
}