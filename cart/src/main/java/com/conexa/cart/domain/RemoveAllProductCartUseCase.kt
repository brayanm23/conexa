package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.repository.CartRepository
import io.reactivex.Completable

class RemoveAllProductCartUseCase(context: Context) {

    var cartRepository = CartRepository(context)

    fun execute(): Completable {
        return cartRepository.deleteAllProductInCart()
    }
}