package com.protsolo.worddefinition.data.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.protsolo.worddefinition.data.repository.local.entity.DefinitionEntity

@Dao
interface WordDefinitionDao {

    @Insert(entity = DefinitionEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDefinition(definitionEntity: DefinitionEntity)

    @Query("SELECT * FROM ${DefinitionEntity.TABLE_NAME} WHERE :word like word")
    suspend fun getDefinition(word: String) : DefinitionEntity?
}