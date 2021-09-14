package com.conexa.cart.repository.api

import com.conexa.cart.model.Cart
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {

    @GET("carts")
    fun getItemsInCart(): Single<List<Cart>>

    @GET("cart/7")
    fun updateCart(@Body cart: Cart): Single<Cart>

}