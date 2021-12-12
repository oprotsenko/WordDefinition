package com.protsolo.worddefinition.data.di


import com.google.gson.Gson
import com.protsolo.worddefinition.data.repository.local.room.RoomDefinitionDataSource
import com.protsolo.worddefinition.data.repository.local.room.WordDefinitionDataBase
import com.protsolo.worddefinition.data.repository.remote.retrofit.RetrofitDefinitionDataSource
import com.protsolo.worddefinition.domain.repository.DefinitionRepository
import com.protsolo.worddefinition.domain.repository.local.ILocalDataSource
import com.protsolo.worddefinition.domain.repository.remote.IRemoteDataSource
import com.protsolo.worddefinition.data.repository.remote.IRequestApi
import org.koin.dsl.module

val definitionDataModule = module {

    single { Gson() }
    single { provideLocaleDataSource(get(), get()) }
    single { provideRemoteDataSource(get()) }
    single { provideDefinitionRepository(get(), get()) }
}

fun provideDefinitionRepository(
    local: ILocalDataSource,
    remote: IRemoteDataSource
): DefinitionRepository =
    DefinitionRepository(local, remote)

fun provideRemoteDataSource(requestApi: IRequestApi): IRemoteDataSource =
    RetrofitDefinitionDataSource(requestApi)

fun provideLocaleDataSource(gson: Gson, roomDatabase: WordDefinitionDataBase): ILocalDataSource =
    RoomDefinitionDataSource(gson, roomDatabase.wordDefinitionDao())