package com.protsolo.worddefinition.di


import com.google.gson.Gson
import com.protsolo.worddefinition.data.DefinitionRepository
import com.protsolo.worddefinition.data.IRepository
import com.protsolo.worddefinition.data.local.ILocalDataSource
import com.protsolo.worddefinition.data.local.room.RoomDefinitionDataSource
import com.protsolo.worddefinition.data.local.room.WordDefinitionDataBase
import com.protsolo.worddefinition.data.remote.IDefinitionApi
import com.protsolo.worddefinition.data.remote.IRemoteDataSource
import com.protsolo.worddefinition.data.remote.retrofit.RetrofitDefinitionDataSource
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
): IRepository =
    DefinitionRepository(local, remote)

fun provideRemoteDataSource(definitionApi: IDefinitionApi): IRemoteDataSource =
    RetrofitDefinitionDataSource(definitionApi)

fun provideLocaleDataSource(gson: Gson, roomDatabase: WordDefinitionDataBase): ILocalDataSource =
    RoomDefinitionDataSource(gson, roomDatabase.wordDefinitionDao())