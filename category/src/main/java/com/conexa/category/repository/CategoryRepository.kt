package com.conexa.category.repository

import com.conexa.category.repository.api.CategoryApi
import com.conexa.networking.RetrofitBuilder
import io.reactivex.Single

class CategoryRepository {

    var catalogapi = RetrofitBuilder.getClient("https://fakestoreapi.com/").create(CategoryApi::class.java)

    fun getCatalog(): Single<List<String>> = catalogapi.getCategories()
}