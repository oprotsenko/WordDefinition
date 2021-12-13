package com.protsolo.worddefinition.di

import com.protsolo.worddefinition.domain.useCases.ClearRecentWords
import com.protsolo.worddefinition.domain.useCases.GetLocalDefinition
import com.protsolo.worddefinition.domain.useCases.SearchDefinition
import com.protsolo.worddefinition.presentation.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        SearchViewModel(get(),get(),get())
    }

    single { GetLocalDefinition(get()) }
    single { ClearRecentWords(get()) }
    single { SearchDefinition(get()) }
}