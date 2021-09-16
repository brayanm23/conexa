package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.repository.CartRepository
import io.reactivex.Completable

class RemoveProductInCardUseCase(context: Context) {

    var cartRepository = CartRepository(context)

    fun execute(id: String): Completable {
        return cartRepository.deleteById(id)
    }

}