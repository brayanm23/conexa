package com.conexa.cart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conexa.cart.domain.*
import com.conexa.cart.model.Product
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CartViewModel(db: AppDatabase): ViewModel() {

    private val showCartUseCase = ShowCartUseCase(db)
    private val updateItemInCartUseCase = UpdateProductInCartUseCase(db)
    private val removeAllProductCartUseCase = RemoveAllProductCartUseCase(db)
    private val removeItemInCardUseCase = RemoveProductInCardUseCase(db)
    private val insertProductUseCase = InsertProductsUseCase(db)

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
                { _uiState.value = CartUiState.EmptyState },
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
        object EmptyState : CartUiState()
        data class DeleteProduct(val id: Int) : CartUiState()
        data class UpdateProduct(val id: Int, val quantity: Int,) : CartUiState()
        data class ShowProducts(val data: List<Product>) : CartUiState()
    }

}