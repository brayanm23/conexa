package com.conexa.cart.domain

import android.content.Context
import com.conexa.cart.model.Product
import com.conexa.cart.repository.CartRepository
import com.conexa.cart.repository.database.AppDatabase
import io.reactivex.Completable

class InsertProductsUseCase(db: AppDatabase) {

    var cartRepository = CartRepository(db)

    fun execute(products: List<Product>): Completable {
        return cartRepository.insertAllProduct(products)
    }

}