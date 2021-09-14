package com.conexa.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.conexa.catalog.databinding.CatalogFragmentBinding
import com.conexa.catalog.model.Product
import com.conexa.catalog.ui.adapter.ProductAdapter
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
                is CatalogUiState.Error -> {
                    hideLoading()
                    showErrorScreen()
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
        viewModel.getCatalog()
        return binding.root
    }

    private fun bindScreen(data: List<Product>) {
        binding.catalog.adapter = ProductAdapter(data)
    }

    /*private fun bindScreen(data: List<Product>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.toProductItem())
        }
        binding.catalog.adapter = groupAdapter
    }

    fun List<Product>.toProductItem() : List<ProductItem>{
        return this.map {
            ProductItem(it)
        }
    }*/

    private fun showErrorScreen() {
    }

    private fun hideLoading() {
    }

    private fun showLoading() {
    }
}