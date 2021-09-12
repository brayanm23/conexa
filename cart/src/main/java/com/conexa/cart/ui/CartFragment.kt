package com.conexa.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.conexa.cart.databinding.CartFragmentBinding
import com.conexa.cart.model.CartResponse
import com.conexa.cart.viewmodel.CartViewModel
import com.conexa.cart.viewmodel.CartViewModel.CartUiState

class CartFragment : Fragment() {

    private lateinit var binding: CartFragmentBinding
    private val viewModel: CartViewModel by activityViewModels()

    private val uiStateObserver: Observer<CartUiState> =
        Observer { uiStateResponse ->
            when (uiStateResponse) {
                is CartUiState.Loading -> {
                    showLoading()
                }

                is CartUiState.ServerError -> {
                    hideLoading()
                    showServerErrorScreen()
                }

                is CartUiState.ConnectionError -> {
                    hideLoading()
                    showConnectionErrorScreen()
                }

                is CartUiState.Success -> {
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
        binding = CartFragmentBinding.inflate(inflater)
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)
        return binding.root
    }

    private fun bindScreen(data: CartResponse) {

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