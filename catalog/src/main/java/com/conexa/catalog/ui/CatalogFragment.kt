package com.conexa.catalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.conexa.catalog.R
import com.conexa.catalog.databinding.CatalogFragmentBinding
import com.conexa.catalog.model.Product
import com.conexa.catalog.ui.adapter.ProductItem
import com.conexa.catalog.viewmodel.CatalogViewModel
import com.conexa.catalog.viewmodel.CatalogViewModel.CatalogUiState
import com.conexa.filter.viewmodel.CategoryViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CatalogFragment : Fragment() {

    private lateinit var binding: CatalogFragmentBinding
    private val viewModel: CatalogViewModel by activityViewModels()
    private val viewModelFilter: CategoryViewModel by activityViewModels()

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
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (viewModelFilter.filter.value.isNullOrBlank())
            viewModel.getCatalog()
        else
            viewModel.getCatalogApplyFilter(viewModelFilter.filter.value!!)
    }

    private fun bindScreen(data: List<Product>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { ProductItem(it) })
        }
        binding.catalog.adapter = groupAdapter
        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment)
        }
    }

    private fun showErrorScreen() {
    }

    private fun hideLoading() {
    }

    private fun showLoading() {
    }
}