package com.protsolo.worddefinition

import android.app.Application
import com.protsolo.worddefinition.di.appModules
import com.protsolo.worddefinition.di.definitionDataModule
import com.protsolo.worddefinition.di.retrofitModules
import com.protsolo.worddefinition.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DefinitionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DefinitionApp)
            modules(listOf(appModules, retrofitModules, definitionDataModule, roomModule))
        }
    }
}