package com.conexa.cart.model

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val quantity: Int,
    val image: String
)