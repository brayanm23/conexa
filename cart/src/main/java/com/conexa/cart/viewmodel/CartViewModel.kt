package com.conexa.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.cart.domain.ShowCartUseCase
import com.conexa.cart.domain.UpdateItemInCartUseCase
import com.conexa.cart.model.Cart
import com.conexa.cart.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CartViewModel : ViewModel() {

    private val showCartUseCase = ShowCartUseCase()
    private val updateItemInCartUseCase = UpdateItemInCartUseCase()
    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<CartUiState>()
    val uiState: LiveData<CartUiState>
        get() = _uiState

    private val _cart = MutableLiveData<Cart>()
    val cart: LiveData<Cart>
        get() = _cart

    fun getCart() {
        disposables.add(showCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { response ->
                    _cart.value = response
                    _uiState.value = CartUiState.Success(response)
                },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    fun updateItemInCart(product: Product) {
        disposables.add(updateItemInCartUseCase.execute(_cart.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { response ->
                    _cart.value = response
                    _uiState.value = CartUiState.Success(response)
                },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    fun removeItemInCart() {
        disposables.add(showCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { response ->
                    _cart.value = response
                    _uiState.value = CartUiState.Success(response)
                },
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
        data class Success(val data: Cart) : CartUiState()
    }

}