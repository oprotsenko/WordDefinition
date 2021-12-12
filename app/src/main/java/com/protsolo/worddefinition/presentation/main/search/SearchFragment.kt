package com.protsolo.worddefinition.presentation.main.search

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.protsolo.worddefinition.base.BaseFragment
import com.protsolo.worddefinition.databinding.FragmentSearchBinding
import com.protsolo.worddefinition.hideKeyboard
import com.protsolo.worddefinition.presentation.main.ISearchDefinitionListener
import com.protsolo.worddefinition.presentation.main.search.adapter.WordAdapter
import com.protsolo.worddefinition.utils.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    IItemClickListener {

    private val viewModel: SearchViewModel by viewModel()
    private val wordAdapter: WordAdapter by lazy { WordAdapter(onItemClickListener = this) }
    private var searchListener: ISearchDefinitionListener? = null
    private var requiredWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchListener = requireActivity() as ISearchDefinitionListener
    }

    override fun recyclerInit() {
        binding.recyclerViewRecent.apply {
            adapter = wordAdapter
            itemAnimator?.changeDuration = 0
        }
        setFabButton()
    }

    override fun setObservers() {
        viewModel.apply {
            wordDefinition.observe(viewLifecycleOwner, {
                binding.editTextSearch.text?.clear()
                searchListener?.onSearchDefinition(it)
            })

            definitionIsNotFound.observe(viewLifecycleOwner, { definitionIsNotFound ->
                if (definitionIsNotFound) {
                    Toast.makeText(
                        requireContext(),
                        Constants.NOT_FOUND_MESSAGE,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

            internetAccess.observe(viewLifecycleOwner, { internetAccess ->
                if (!internetAccess) {
                    Toast.makeText(
                        requireContext(),
                        Constants.NO_INTERNET_MESSAGE,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

            recentWordsData.observe(viewLifecycleOwner, {
                wordAdapter.submitList(it)
            })
        }
    }

    override fun setListeners() {
        binding.apply {
            buttonSearch.setOnClickListener {
                requiredWord = editTextSearch.text.toString()
                it.hideKeyboard()
                if (requiredWord.isNotEmpty()) {
                    viewModel.fetchData(requiredWord)
                }
            }

            fButtonClear.setOnClickListener {
                viewModel.apply {
                    viewModelScope.launch {
                        clearRecentWords()
                    }
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.apply {
            val word = recentWordsData.value?.get(position)
            word?.let {
                viewModelScope.launch {
                    wordDefinition.value = getLocalWord(word)
                }
            }
        }
    }

    private fun setFabButton() {
        with(binding) {
            recyclerViewRecent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val positionView =
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()

                    if (positionView > 0) {
                        if (!fButtonClear.isShown) {
                            fButtonClear.show()
                        }
                    } else {
                        if (fButtonClear.isShown) {
                            fButtonClear.hide()
                        }
                    }
                }
            })
        }
    }
}