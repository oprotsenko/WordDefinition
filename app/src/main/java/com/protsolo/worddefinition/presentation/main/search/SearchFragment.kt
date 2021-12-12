package com.protsolo.worddefinition.presentation.main.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.protsolo.worddefinition.databinding.FragmentSearchBinding
import com.protsolo.worddefinition.hideKeyboard
import com.protsolo.worddefinition.presentation.base.BaseFragment
import com.protsolo.worddefinition.presentation.main.ISearchDefinitionListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModel()
    private var searchListener: ISearchDefinitionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchListener = requireActivity() as ISearchDefinitionListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            var requiredWord = ""
            buttonSearch.setOnClickListener {
                requiredWord = editTextSearch.text.toString()
                it.hideKeyboard()
                viewModel.fetchData(requiredWord)
                editTextSearch.text?.clear()
            }

            viewModel.data.observe(viewLifecycleOwner, {
                searchListener?.onSearchDefinition(it)
            })

            viewModel.definitionIsNotFound.observe(viewLifecycleOwner, { definitionIsNotFound ->
                if (definitionIsNotFound) {
                    Toast.makeText(
                        requireContext(),
                        "'$requiredWord' is not found!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}