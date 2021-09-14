package com.conexa.cart.domain

import com.conexa.cart.model.Cart
import com.conexa.cart.repository.CartRepository
import io.reactivex.Single

class UpdateCartUseCase {

    var cartRepository = CartRepository()

    fun execute(cart: Cart): Single<Cart> {
        return cartRepository.updateCart(cart)
    }

}