package com.conexa.catalog.repository.api

import com.conexa.catalog.model.CatalogResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {

    @GET("products")
    fun getProducts(): Single<CatalogResponse>

    /*@GET("products/categories")
    fun getCategories(): Single<ChargeAmountsResponse>*/

    @GET("products/category/{idCategory}")
    fun getProductsSpecificCategory(@Path("idCategory") idCategory: String): Single<CatalogResponse>

}