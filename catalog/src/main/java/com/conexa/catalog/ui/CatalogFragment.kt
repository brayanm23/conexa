package com.conexa.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.conexa.catalog.databinding.CatalogFragmentBinding
import com.conexa.catalog.model.CatalogResponse
import com.conexa.catalog.viewmodel.CatalogViewModel
import com.conexa.catalog.viewmodel.CatalogViewModel.CatalogUiState

class CatalogFragment : Fragment() {

    private lateinit var binding: CatalogFragmentBinding
    private val viewModel: CatalogViewModel by activityViewModels()

    private val uiStateObserver: Observer<CatalogUiState> =
        Observer { uiStateResponse ->
            when (uiStateResponse) {
                is CatalogUiState.Loading -> {
                    showLoading()
                }

                is CatalogUiState.ServerError -> {
                    hideLoading()
                    showServerErrorScreen()
                }

                is CatalogUiState.ConnectionError -> {
                    hideLoading()
                    showConnectionErrorScreen()
                }

                is CatalogUiState.Success -> {
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
        binding = CatalogFragmentBinding.inflate(inflater)
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
        return binding.root
    }

    private fun bindScreen(data: CatalogResponse) {

    }

    private fun showConnectionErrorScreen() {
        TODO("Not yet implemented")
    }

    private fun showServerErrorScreen() {
        TODO("Not yet implemented")
    }

    private fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }
}