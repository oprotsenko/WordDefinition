package com.protsolo.worddefinition.di

import android.content.Context
import androidx.room.Room
import com.protsolo.worddefinition.data.local.room.WordDefinitionDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single { provideDataBase(androidContext()) }
}

fun provideDataBase(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        WordDefinitionDataBase::class.java,
        "word_definition_database"
    ).build()
