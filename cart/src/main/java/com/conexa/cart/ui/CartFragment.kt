package com.conexa.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.conexa.cart.databinding.CartFragmentBinding
import com.conexa.cart.model.Product
import com.conexa.cart.ui.adapter.CartItem
import com.conexa.cart.viewmodel.CartViewModel
import com.conexa.cart.viewmodel.CartViewModel.CartUiState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class CartFragment : Fragment(), CartItem.OnClickListener {

    private lateinit var binding: CartFragmentBinding
    private val viewModel: CartViewModel by activityViewModels()

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
                is CartUiState.DeleteAll -> {}
                is CartUiState.DeleteProduct -> {}
                is CartUiState.UpdateProduct -> {}
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

    override fun onDeleteClick(id: Int) {
        viewModel.removeProductInCart(id)
    }

    override fun onUpdateClick(id: Int, quantity: Int) {
        viewModel.updateItemInCart(id, quantity)
    }
}