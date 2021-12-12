package com.protsolo.worddefinition.presentation.main

import com.protsolo.worddefinition.domain.model.WordDefinitionItem

interface ISearchDefinitionListener {
    fun onSearchDefinition(wordDefinitionItem: WordDefinitionItem)
}