package com.conexa.cart.repository

import com.conexa.cart.model.Cart
import com.conexa.cart.repository.api.CartApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.Single

class CartRepository {

    var cartapi = RetrofitBuilder.getClient("https://fakestoreapi.com/").create(CartApi::class.java)

    fun getItemsInCart(): Single<List<Cart>> = cartapi.getItemsInCart()
}