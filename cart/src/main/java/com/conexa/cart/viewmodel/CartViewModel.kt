package com.conexa.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.cart.domain.ShowCartUseCase
import com.conexa.cart.model.Cart
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CartViewModel : ViewModel() {

    private val showCartUseCase = ShowCartUseCase()

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<CartUiState>()
    val uiState: LiveData<CartUiState>
        get() = _uiState

    fun getItemsInCard() {
        disposables.add(showCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { response -> _uiState.setValue(CartUiState.Success(response)) },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

    sealed class CartUiState {
        object Loading : CartUiState()
        object Error : CartUiState()
        data class Success(val data: List<Cart>) : CartUiState()
    }

}