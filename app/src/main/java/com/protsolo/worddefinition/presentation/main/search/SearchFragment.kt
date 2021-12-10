package com.protsolo.worddefinition.presentation.main.search

import android.os.Bundle
import android.view.View
import com.protsolo.worddefinition.databinding.FragmentSearchBinding
import com.protsolo.worddefinition.hideKeyboard
import com.protsolo.worddefinition.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            buttonSearch.setOnClickListener {
                it.hideKeyboard()
                viewModel.fetchData(editTextSearch.text.toString())
                editTextSearch.text?.clear()
            }
//            text.text = viewModel.data.value?.word
            viewModel.data.observe(viewLifecycleOwner, {
//                text.text = it.word
            })
        }
    }
}