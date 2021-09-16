package com.conexa.cart.domain

import com.conexa.cart.repository.CartRepository
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.Completable

class RemoveProductInCardUseCase(db: AppDatabase) {

    var cartRepository = CartRepository(db)

    fun execute(id: String): Completable {
        return cartRepository.deleteById(id)
    }

}