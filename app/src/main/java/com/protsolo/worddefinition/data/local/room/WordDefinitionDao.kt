package com.protsolo.worddefinition.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.protsolo.worddefinition.data.local.entity.DefinitionEntity
import com.protsolo.worddefinition.utils.Constants.TABLE_NAME

@Dao
interface WordDefinitionDao {

    @Insert(entity = DefinitionEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDefinition(definitionEntity: DefinitionEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE :word like word")
    suspend fun getDefinition(word: String) : DefinitionEntity?

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllData() : List<DefinitionEntity>?

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearData()
}