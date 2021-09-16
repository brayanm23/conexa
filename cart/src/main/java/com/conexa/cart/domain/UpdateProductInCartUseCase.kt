package com.conexa.cart.domain

import com.conexa.cart.repository.CartRepository
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.Single

class UpdateProductInCartUseCase(db: AppDatabase) {

    var cartRepository = CartRepository(db)

    fun execute(id: Int, quantity: Int): Single<Unit> {
        return cartRepository.updateProductInCart(id, quantity)
    }

}