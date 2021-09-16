package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.model.Product
import com.conexa.cart.repository.CartRepository
import io.reactivex.Completable

class InsertProductsUseCase(context: Context) {

    var cartRepository = CartRepository(context)

    fun execute(products: List<Product>): Completable {
        return cartRepository.insertAllProduct(products)
    }

}