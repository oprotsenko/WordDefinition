package com.protsolo.worddefinition.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.protsolo.worddefinition.R
import com.protsolo.worddefinition.domain.model.WordDefinitionItem
import com.protsolo.worddefinition.presentation.main.definition.DefinitionFragment

class MainActivity : AppCompatActivity(), ISearchDefinitionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSearchDefinition(wordDefinitionItem: WordDefinitionItem) {
        (supportFragmentManager.findFragmentById(R.id.definitionFragment) as DefinitionFragment).setDefinition(
            wordDefinitionItem
        )
    }
}