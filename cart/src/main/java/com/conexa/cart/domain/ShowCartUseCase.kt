package com.conexa.cart.domain

import com.conexa.cart.model.Product
import com.conexa.cart.repository.CartRepository
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.Single

class ShowCartUseCase(db: AppDatabase) {

    var cartRepository = CartRepository(db)

    fun execute(): Single<List<Product>> {
        return cartRepository.getItemsInCart()
    }

}