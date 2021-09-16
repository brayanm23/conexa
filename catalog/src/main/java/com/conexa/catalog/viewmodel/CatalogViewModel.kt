package com.conexa.catalog.viewmodel

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.cart.viewmodel.CartViewModel
import com.conexa.catalog.domain.ShowCatalogApplyFilterUseCase
import com.conexa.catalog.domain.ShowCatalogUseCase
import com.conexa.catalog.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatalogViewModel : ViewModel() {
    private val showCatalogUseCase = ShowCatalogUseCase()
    private val showCatalogApplyFilterUseCase = ShowCatalogApplyFilterUseCase()

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _uiState = MutableLiveData<CatalogUiState>()
    val uiState: LiveData<CatalogUiState>
        get() = _uiState

    fun getCatalog() {
        disposables.add(showCatalogUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CatalogUiState.Loading) }
            .subscribe(
                { response -> _uiState.setValue(CatalogUiState.Success(response)) },
                { throwable -> _uiState.setValue(CatalogUiState.Error) }
            )
        )
    }

    fun getCatalogApplyFilter(category: String) {
        disposables.add(showCatalogApplyFilterUseCase.execute(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CatalogUiState.Loading) }
            .subscribe(
                { response ->
                    _products.value = response
                    _uiState.value = CatalogUiState.Success(response)
                },
                { throwable -> _uiState.setValue(CatalogUiState.Error) }
            )
        )
    }

    fun search(searchText: String): List<Product>? {
        products.value?.let {
            return it.filter { product ->
                searchText.toLowerCase().contains(product.title.toLowerCase())
            }
        }?: run {
            Log.d("brayan lista", "_product es null")
            return null
        }
    }

    override fun onCleared() {
        disposables.clear()
    }

    sealed class CatalogUiState {
        object Loading : CatalogUiState()
        object Error : CatalogUiState()
        data class Success(val data: List<Product>) : CatalogUiState()
    }

}