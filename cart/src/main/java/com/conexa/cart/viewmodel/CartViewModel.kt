package com.conexa.cart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.conexa.cart.domain.*
import com.conexa.cart.model.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CartViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val showCartUseCase = ShowCartUseCase(context)
    private val updateItemInCartUseCase = UpdateProductInCartUseCase(context)
    private val removeAllProductCartUseCase = RemoveAllProductCartUseCase(context)
    private val removeItemInCardUseCase = RemoveProductInCardUseCase(context)
    private val insertProductUseCase = InsertProductsUseCase(context)

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<CartUiState>()
    val uiState: LiveData<CartUiState>
        get() = _uiState

    fun getProductsInCart() {
        disposables.add(showCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { response ->
                    _uiState.value = CartUiState.ShowProducts(response)
                },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    fun insertProducts(products: List<Product>) {
        disposables.add(insertProductUseCase.execute(products)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe()
        )
    }

    fun updateItemInCart(id: Int, quantity: Int) {
        disposables.add(updateItemInCartUseCase.execute(id,quantity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { _uiState.value = CartUiState.UpdateProduct(id, quantity) },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    fun removeProductInCart(id: Int) {
        disposables.add(removeItemInCardUseCase.execute(id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { _uiState.value = CartUiState.DeleteProduct(id) },
                { throwable -> _uiState.setValue(CartUiState.Error) }
            )
        )
    }

    fun removeAllProductInCart() {
        disposables.add(removeAllProductCartUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.setValue(CartUiState.Loading) }
            .subscribe(
                { _uiState.value = CartUiState.DeleteAll },
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
        object DeleteAll : CartUiState()
        data class DeleteProduct(val id: Int) : CartUiState()
        data class UpdateProduct(val id: Int, val quantity: Int,) : CartUiState()
        data class ShowProducts(val data: List<Product>) : CartUiState()
    }

}