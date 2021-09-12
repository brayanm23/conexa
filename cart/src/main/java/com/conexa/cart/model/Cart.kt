package com.conexa.cart.model

data class Cart(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<Product>
)