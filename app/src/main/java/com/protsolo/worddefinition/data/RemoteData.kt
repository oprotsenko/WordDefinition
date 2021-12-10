package com.protsolo.worddefinition.data

import com.protsolo.worddefinition.domain.retrofit.RequestApiProvider

class RemoteData(private val requestApiProvider: RequestApiProvider) {
    suspend fun getDefinition(word: String) = requestApiProvider.getRequestApi().getDefinition(word)
}