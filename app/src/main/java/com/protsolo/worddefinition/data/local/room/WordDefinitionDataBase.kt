package com.protsolo.worddefinition.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.protsolo.worddefinition.data.local.entity.DefinitionEntity

@Database(entities = [
    DefinitionEntity::class
], version = 1,exportSchema = false)
abstract class WordDefinitionDataBase : RoomDatabase() {
    abstract fun wordDefinitionDao() : WordDefinitionDao
}