package com.conexa.cart.repository.database.dao

import androidx.room.*
import com.conexa.cart.model.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllProduct(products: List<Product>): Completable

    @Query("SELECT * FROM ${Product.TABLE_NAME} WHERE ${Product.COLUMN_NAME_QUANTITY} > 0")
    fun getProductInCart(): Single<List<Product>>

    @Query("UPDATE ${Product.TABLE_NAME} SET ${Product.COLUMN_NAME_QUANTITY} = :quantity WHERE ${Product.COLUMN_NAME_ID} = :id")
    fun updateProductInCart(id: Int, quantity: Int): Single<Unit>

    @Query("UPDATE ${Product.TABLE_NAME} SET ${Product.COLUMN_NAME_QUANTITY} = 0 WHERE ${Product.COLUMN_NAME_ID} = :id")
    fun deleteById(id: String): Completable

    @Query("UPDATE ${Product.TABLE_NAME} SET ${Product.COLUMN_NAME_QUANTITY} = 0")
    fun deleteAllProductInCart(): Completable
}