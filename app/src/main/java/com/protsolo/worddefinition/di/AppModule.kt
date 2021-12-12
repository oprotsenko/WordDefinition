package com.protsolo.worddefinition.di

import com.protsolo.worddefinition.domain.useCases.RecentDefinition
import com.protsolo.worddefinition.domain.useCases.SearchDefinition
import com.protsolo.worddefinition.presentation.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        SearchViewModel(get(), get())
    }

    single { RecentDefinition(get()) }
    single { SearchDefinition(get()) }
}