package com.conexa.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.conexa.category.databinding.CategoryFragmentBinding
import com.conexa.category.viewmodel.CategoryViewModel
import com.conexa.category.viewmodel.CategoryViewModel.CategoryUiState

class CategoryFragment : Fragment() {

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
        return binding.root
    }

    private fun bindScreen(data: List<String>) {

    }

    private fun showErrorScreen() {
        TODO("Not yet implemented")
    }

    private fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }
}