package com.conexa.cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Product.TABLE_NAME)
data class Product(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_NAME_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_NAME_TITLE)
    val title: String,
    @ColumnInfo(name = COLUMN_NAME_PRICE)
    val price: String,
    @ColumnInfo(name = COLUMN_NAME_CATEGORY)
    val category: String,
    @ColumnInfo(name = COLUMN_NAME_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = COLUMN_NAME_QUANTITY)
    var quantity: Int,
    @ColumnInfo(name = COLUMN_NAME_IMAGE)
    val image: String
) {
    companion object {
        const val TABLE_NAME = "product"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TITLE: String = "title"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_CATEGORY: String = "category"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_QUANTITY: String = "quantity"
        const val COLUMN_NAME_IMAGE: String = "image"
    }
}