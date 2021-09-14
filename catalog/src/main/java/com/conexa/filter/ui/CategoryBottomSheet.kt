package com.conexa.filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.conexa.catalog.R
import com.conexa.catalog.databinding.CategoryFragmentBinding
import com.conexa.filter.ui.adapter.CategoryItem
import com.conexa.filter.viewmodel.CategoryViewModel
import com.conexa.filter.viewmodel.CategoryViewModel.CategoryUiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CategoryBottomSheet : BottomSheetDialogFragment(), CategoryItem.OnClickItemListener {

    private lateinit var binding: CategoryFragmentBinding
    private val viewModel: CategoryViewModel by activityViewModels()

    private val uiStateObserver: Observer<CategoryUiState> =
        Observer { uiStateResponse ->
            when (uiStateResponse) {
                is CategoryUiState.Loading -> {
                    showLoading()
                }
                is CategoryUiState.Error -> {
                    hideLoading()
                    showErrorScreen()
                }
                is CategoryUiState.Success -> {
                    hideLoading()
                    bindScreen(uiStateResponse.data)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CategoryFragmentBinding.inflate(inflater)
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
        viewModel.getCategories()
        return binding.root
    }

    override fun onItemClick(category: String) {
        viewModel.saveCategoryForFilter(category)
        findNavController().navigate(R.id.action_catalogFragment)
    }

    private fun bindScreen(data: List<String>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { CategoryItem(it, this@CategoryBottomSheet) })
        }
        binding.categories.adapter = groupAdapter
    }

    private fun showErrorScreen() {
    }

    private fun hideLoading() {
    }

    private fun showLoading() {
    }
}