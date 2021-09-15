package com.conexa.cart.domain

import com.conexa.cart.model.Cart
import com.conexa.cart.repository.CartRepository
import io.reactivex.Single

class ShowCartUseCase {

    var cartRepository = CartRepository()

    fun execute(): Single<Cart> {
        return cartRepository.getItemsInCart()
    }

}