package com.conexa.category.repository.api

import com.conexa.category.model.CategoryResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryApi {

    @GET("products/categories")
    fun getCategories(): Single<CategoryResponse>

}