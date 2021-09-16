package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.repository.CartRepository
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.Completable

class RemoveAllProductCartUseCase(db: AppDatabase) {

    var cartRepository = CartRepository(db)

    fun execute(): Completable {
        return cartRepository.deleteAllProductInCart()
    }
}