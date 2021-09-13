package com.conexa.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.catalog.domain.ShowCatalogUseCase
import com.conexa.catalog.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatalogViewModel : ViewModel() {

    private val showCatalogUseCase = ShowCatalogUseCase()

    private val disposables: CompositeDisposable = CompositeDisposable()

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

    override fun onCleared() {
        disposables.clear()
    }

    sealed class CatalogUiState {
        object Loading : CatalogUiState()
        object Error : CatalogUiState()
        data class Success(val data: List<Product>) : CatalogUiState()
    }

}