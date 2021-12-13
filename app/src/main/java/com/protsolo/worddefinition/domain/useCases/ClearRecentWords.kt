package com.protsolo.worddefinition.domain.useCases

import com.protsolo.worddefinition.data.IRepository

class ClearRecentWords(private val repository: IRepository) {
    suspend fun clearData() {
        repository.clearData()
    }
}