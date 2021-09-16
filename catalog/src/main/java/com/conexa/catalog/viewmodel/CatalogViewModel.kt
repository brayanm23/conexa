package com.conexa.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.catalog.domain.ShowCatalogApplyFilterUseCase
import com.conexa.catalog.domain.ShowCatalogUseCase
import com.conexa.cart.model.Product
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
                { _uiState.setValue(CatalogUiState.Error) }
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
                { _uiState.setValue(CatalogUiState.Error) }
            )
        )
    }

    fun search(searchText: String) = products.value?.filter { product ->
        searchText.toLowerCase().contains(product.title.toLowerCase())
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