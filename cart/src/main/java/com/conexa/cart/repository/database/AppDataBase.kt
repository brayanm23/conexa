package com.conexa.cart.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conexa.cart.repository.database.dao.ProductDao
import com.conexa.cart.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val NAME = "CONEXA_CART_DB"
    }

    abstract fun productDao(): ProductDao

}