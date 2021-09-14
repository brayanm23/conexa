package com.conexa.catalog.repository.api

import com.conexa.catalog.model.Product
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface CatalogApi {

    @GET("products")
    fun getProducts(): Single<List<Product>>

    @GET("products/category/{idCategory}")
    fun getProductsSpecificCategory(@Path("idCategory") idCategory: String): Single<List<Product>>

}