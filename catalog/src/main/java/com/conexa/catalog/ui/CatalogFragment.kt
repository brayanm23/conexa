package com.conexa.catalog.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.conexa.cart.model.Product
import com.conexa.cart.repository.database.DatabaseBuilder
import com.conexa.cart.viewmodel.CartViewModel
import com.conexa.cart.viewmodel.ViewModelFactory
import com.conexa.catalog.R
import com.conexa.catalog.databinding.CatalogFragmentBinding
import com.conexa.catalog.ui.adapter.ProductItem
import com.conexa.catalog.viewmodel.CatalogViewModel
import com.conexa.catalog.viewmodel.CatalogViewModel.CatalogUiState
import com.conexa.filter.viewmodel.CategoryViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class CatalogFragment : Fragment(), ProductItem.OnClickListener {

    private lateinit var binding: CatalogFragmentBinding
    private val viewModel: CatalogViewModel by viewModels()
    private val viewModelFilter: CategoryViewModel by activityViewModels()
    private val viewModelCart: CartViewModel by viewModels { ViewModelFactory(DatabaseBuilder.getInstance(requireContext())) }

    private val textWatcher: TextWatcher = object : TextWatcher {
        val subject: PublishSubject<CharSequence> = PublishSubject.create()

        init {
            subject.debounce(750, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { viewModel.search(binding.search.text.toString())?.let { bindScreen(it) } }, {}
                )
        }

        override fun afterTextChanged(s: Editable?) {
            subject.onNext(s ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

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
        binding = CatalogFragmentBinding.inflate(inflater).apply {
            search.addTextChangedListener(textWatcher)
            filter.setOnClickListener {
                if (viewModelFilter.filter.value.isNullOrBlank()) {
                    findNavController().navigate(R.id.action_categoryFragment)
                } else {
                    viewModel.getCatalog()
                    filter.setImageResource(R.drawable.ic_filter)
                }
            }
            cart.setOnClickListener { findNavController().navigate(R.id.action_cartFragment) }
        }
        viewModel.uiState.observe(viewLifecycleOwner, uiStateObserver)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (viewModelFilter.filter.value.isNullOrBlank()) {
            viewModel.getCatalog()
        } else {
            viewModel.getCatalogApplyFilter(viewModelFilter.filter.value!!)
            binding.filter.setImageResource(R.drawable.ic_close)
        }
    }

    private fun bindScreen(data: List<Product>) {
        viewModelCart.insertProducts(data)
        binding.catalog.visibility = View.VISIBLE
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(data.map { ProductItem(it, this@CatalogFragment) })
        }
        binding.catalog.adapter = groupAdapter
    }

    private fun showErrorScreen() {
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.catalog.visibility = View.GONE

    }

    override fun onClickAddInCart(id: Int) {
        viewModelCart.updateItemInCart(id, 1)
    }
}