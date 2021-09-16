package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.repository.CartRepository
import io.reactivex.Single

class UpdateProductInCartUseCase(context: Context) {

    var cartRepository = CartRepository(context)

    fun execute(id: Int, quantity: Int): Single<Unit> {
        return cartRepository.updateProductInCart(id, quantity)
    }

}