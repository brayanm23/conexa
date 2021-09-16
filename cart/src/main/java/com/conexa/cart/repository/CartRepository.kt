package com.conexa.cart.repository

import com.conexa.cart.repository.database.AppDatabase
import com.conexa.cart.model.Product
import io.reactivex.Completable
import io.reactivex.Single

class CartRepository(private val db: AppDatabase) {

    fun insertAllProduct(products: List<Product>): Completable = db.productDao().insertAllProduct(products)

    fun getItemsInCart(): Single<List<Product>> = db.productDao().getProductInCart()

    fun updateProductInCart(id: Int, quantity: Int): Single<Unit> = db.productDao().updateProductInCart(id, quantity)

    fun deleteById(id: String): Completable = db.productDao().deleteById(id)

    fun deleteAllProductInCart(): Completable = db.productDao().deleteAllProductInCart()
}