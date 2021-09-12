package com.conexa.cart.repository.api

import com.conexa.cart.model.CartResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {

    @GET("products")
    fun getProducts(): Single<CartResponse>

    /*@GET("products/categories")
    fun getCategories(): Single<ChargeAmountsResponse>*/

    @GET("products/category/{idCategory}")
    fun getProductsSpecificCategory(@Path("idCategory") idCategory: String): Single<CartResponse>

}