package com.conexa.cart.repository.api

import com.conexa.cart.model.Cart
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {

    @GET("carts/5")
    fun getItemsInCart(): Single<Cart>

    @GET("cart/5")
    fun updateCart(@Body cart: Cart): Single<Cart>

}