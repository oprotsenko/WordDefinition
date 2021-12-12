package com.protsolo.worddefinition.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.protsolo.worddefinition.domain.model.Meaning
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.utils.Constants.TABLE_NAME
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Type

@Entity(tableName = TABLE_NAME)@Parcelize
data class DefinitionEntity(
    @PrimaryKey
    val word: String,
    val phonetic: String?,
    val meanings: String?,
) : Parcelable

fun DefinitionEntity.mapToRemote(gson: Gson): WordDefinitionItem {

    val type: Type = object : TypeToken<List<Meaning>>() {}.type

    return WordDefinitionItem(
        word = this.word,
        phonetic = this.phonetic.toString(),
        meanings = gson.fromJson(this.meanings, type)
    )
}