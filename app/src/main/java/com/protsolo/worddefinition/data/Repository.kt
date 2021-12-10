package com.protsolo.worddefinition.data

class Repository(private val remoteData: RemoteData) {
    suspend fun getDefinition(word: String) = remoteData.getDefinition(word)
}