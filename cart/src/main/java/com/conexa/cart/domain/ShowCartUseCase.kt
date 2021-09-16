package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.model.Product
import com.conexa.cart.repository.CartRepository
import io.reactivex.Single

class ShowCartUseCase(context: Context) {

    var cartRepository = CartRepository(context)

    fun execute(): Single<List<Product>> {
        return cartRepository.getItemsInCart()
    }

}