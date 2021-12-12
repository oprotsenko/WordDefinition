package com.protsolo.worddefinition.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class WordDefinition : ArrayList<WordDefinitionItem>()

@Parcelize
data class WordDefinitionItem(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>?,
) : Parcelable

@Parcelize
data class Meaning(
    val partOfSpeech: String?,
    val definitions: List<Definition>?
) : Parcelable

@Parcelize
data class Definition(
    val definition: String?
) : Parcelable