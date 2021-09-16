package com.conexa.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.conexa.cart.databinding.CartFragmentBinding
import com.conexa.cart.model.Product
import com.conexa.cart.repository.database.DatabaseBuilder
import com.conexa.cart.ui.adapter.CartItem
import com.conexa.cart.viewmodel.CartViewModel
import com.conexa.cart.viewmodel.CartViewModel.CartUiState
import com.conexa.cart.viewmodel.ViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CartFragment : Fragment(), CartItem.OnClickListener {

    private lateinit var binding: CartFragmentBinding
    private val viewModel: CartViewModel by viewModels { ViewModelFactory(DatabaseBuilder.getInstance(requireContext())) }

    private val uiStateObserver: Observer<CartUiState> =
        Observer { uiStateResponse ->
            when (uiStateResponse) {
                is CartUiState.Loading -> {
                    showLoading()
                }
                is CartUiState.Error -> {
                    hideLoading()
                    showErrorScreen()
                }
                is CartUiState.ShowProducts -> {
                    hideLoading()
                    bindScreen(uiStateResponse.data)
                }
                is CartUiState.EmptyState -> {
                    hideLoading()
                    showEmptyState()
                }
                is CartUiState.DeleteProduct -> {
                    hideLoading()
                    deleteProduct(uiStateResponse.id)
                }
                is CartUiState.UpdateProduct -> {
                    hideLoading()
                    updateProduct(uiStateResponse.id, uiStateResponse.quantity)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(inflater)
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
        viewModel.getProductsInCart()
        return binding.root
    }

    private fun bindScreen(data: List<Product>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { CartItem(it, this@CartFragment) })
        }
        binding.products.adapter = groupAdapter
    }

    private fun showErrorScreen() {
    }

    private fun hideLoading() {
    }

    private fun showLoading() {
    }

    private fun deleteProduct(id: Int) {
    }

    private fun updateProduct(id: Int, quantity: Int) {

    }

    private fun showEmptyState() {
    }

    override fun onDeleteClick(id: Int) {
        viewModel.removeProductInCart(id)
    }

    override fun onUpdateClick(id: Int, quantity: Int) {
        viewModel.updateItemInCart(id, quantity)
    }
}