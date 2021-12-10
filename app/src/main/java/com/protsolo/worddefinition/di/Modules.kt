package com.protsolo.worddefinition.di

import com.protsolo.worddefinition.data.RemoteData
import com.protsolo.worddefinition.data.Repository
import com.protsolo.worddefinition.domain.retrofit.BaseUrl
import com.protsolo.worddefinition.domain.retrofit.RequestApiProvider
import com.protsolo.worddefinition.domain.retrofit.RetrofitProvider
import com.protsolo.worddefinition.presentation.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    viewModel {
        SearchViewModel(get())
    }
}

val retrofitModules = module {
    single { Repository(get()) }
    single { RemoteData(get()) }
    single { RequestApiProvider(get(), get()) }
    single { RetrofitProvider() }
    single { BaseUrl() }
}