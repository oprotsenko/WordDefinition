package com.protsolo.worddefinition.data.repository.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.protsolo.worddefinition.data.repository.local.entity.DefinitionEntity.Companion.TABLE_NAME
import com.protsolo.worddefinition.domain.model.Meaning
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Type

@Entity(tableName = TABLE_NAME)@Parcelize
data class DefinitionEntity(
    @PrimaryKey @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "phonetic")
    val phonetic: String?,
    @ColumnInfo(name = "meanings")
    val meanings: String?,
) : Parcelable {
    companion object {
        const val TABLE_NAME = "word_definition_entity_table"
    }
}

fun DefinitionEntity.mapToRemote(gson: Gson): WordDefinitionItem {

    val type: Type = object : TypeToken<List<Meaning>>() {}.type

    return WordDefinitionItem(
        word = this.word,
        phonetic = this.phonetic.toString(),
        meanings = gson.fromJson(this.meanings, type)
    )
}

//@Parcelize
//data class Meaning(
//    @ColumnInfo(name = "partOfSpeech")
//    val partOfSpeech: String?,
//    @ColumnInfo(name = "definitions")
//    val definitions: String?
//) : Parcelable
//
//@Parcelize
//data class Definition(
//    @ColumnInfo(name = "definition")
//    val definition: String?,
//) : Parcelable