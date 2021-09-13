package com.conexa.category.repository.api

import io.reactivex.Single
import retrofit2.http.GET

interface CategoryApi {

    @GET("products/categories")
    fun getCategories(): Single<List<String>>

}